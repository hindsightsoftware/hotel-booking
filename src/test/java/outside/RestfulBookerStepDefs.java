package outside;

import api.Application;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Auth;
import model.Booking;
import model.CreatedBooking;
import model.Token;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.springframework.boot.SpringApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class RestfulBookerStepDefs {

    private Booking booking;
    private SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    private Response httpResponse;

    @Before
    public static void setup() throws SQLException {
        try {
            SpringApplication.run(Application.class);
        } catch (Exception e ){
            System.out.println(e.toString());
        }

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:" + System.getProperty("user.dir") + "/booking.db");
        ds.setUser("sa");
        ds.setPassword("sa");
        Connection conn = ds.getConnection();

        conn.prepareStatement("DELETE FROM bookings").execute();
        conn.prepareStatement("ALTER TABLE bookings ALTER COLUMN id RESTART WITH 1").execute();
    }

    @Given("^a user wants to make a booking with the following details$")
    public void createBookingPayload(List<String> table) throws Exception {
        Date checkin = dateParser.parse(table.get(4));
        Date checkout = dateParser.parse(table.get(5));

        booking = new Booking.BookingBuilder()
                             .setFirstname(table.get(0))
                             .setLastname(table.get(1))
                             .setTotalprice(Integer.decode(table.get(2)))
                             .setDepositpaid(Boolean.getBoolean(table.get(3)))
                             .setCheckin(checkin)
                             .setCheckout(checkout)
                             .setAdditionalneeds(table.get(6))
                             .build();
    }

    @When("^the booking is submitted by the user$")
    public void sendBookingPayload() throws Exception {
        httpResponse = given()
                        .body(booking)
                        .contentType(ContentType.JSON)
                       .when()
                        .post("/booking");
    }

    @Then("^the booking is successfully stored$")
    public void assertBookingResponse() throws Exception {
        httpResponse.then().statusCode(200);
    }

    @Then("^shown to the user as stored$")
    public void shown_to_the_user_as_stored() throws Exception {
        String responseBooking = httpResponse.getBody().prettyPrint();
        String expectedResponse = "{\n" +
                "    \"bookingid\": 1,\n" +
                "    \"booking\": {\n" +
                "        \"firstname\": \"Mark\",\n" +
                "        \"lastname\": \"Winters\",\n" +
                "        \"totalprice\": 120,\n" +
                "        \"depositpaid\": false,\n" +
                "        \"additionalneeds\": \"Breakfast\",\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2018-01-01\",\n" +
                "            \"checkout\": \"2018-01-03\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        Assert.assertThat(expectedResponse,is(responseBooking));
    }

    @Given("^RestfulBooker has existing bookings$")
    public void restfulbooker_has_existing_bookings() throws Exception {
        httpResponse = createBooking();
    }

    @When("^a specific booking is requested by the user$")
    public void requestBooking() throws Exception {
        httpResponse = given()
                        .accept(ContentType.JSON)
                        .get("/booking/1");
    }

    @Then("^the booking is shown$")
    public void the_booking_is_shown() throws Exception {
        String responseBooking = httpResponse.body().prettyPrint();
        String expectedResponse = "{\n" +
                "    \"firstname\": \"Mark\",\n" +
                "    \"lastname\": \"Winteringham\",\n" +
                "    \"totalprice\": 123,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"additionalneeds\": \"Breakfast\",\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-02-01\",\n" +
                "        \"checkout\": \"2018-02-02\"\n" +
                "    }\n" +
                "}";

        Assert.assertThat(expectedResponse,is(responseBooking.toString()));
    }

    Response authResponse;

    @Given("^the user is authenticated$")
    public void logIn() throws Exception {
        Auth auth = new Auth("admin", "password123");

        authResponse = given()
                        .body(auth)
                        .contentType(ContentType.JSON)
                       .when()
                        .post("/auth");
    }

    @When("^a specific booking is deleted by the user$")
    public void createBookingAndDeleteIt() throws Exception {
        Response createdBooking = createBooking();
        int id = createdBooking.as(CreatedBooking.class).getBookingid();
        String token = authResponse.as(Token.class).getToken();

        httpResponse = given()
                        .cookie("token", token)
                       .when()
                        .delete("/booking/" + id);
    }

    @Then("^the booking is removed$")
    public void the_booking_is_removed() throws Exception {
        Assert.assertThat(httpResponse.statusCode(), is(202));
    }

    private Response createBooking() throws ParseException {
        Date checkin = dateParser.parse("2018-02-01");
        Date checkout = dateParser.parse("2018-02-02");

        Booking booking = new Booking.BookingBuilder()
                                     .setFirstname("Mark")
                                     .setLastname("Winteringham")
                                     .setTotalprice(123)
                                     .setDepositpaid(true)
                                     .setCheckin(checkin)
                                     .setCheckout(checkout)
                                     .setAdditionalneeds("Breakfast")
                                     .build();

        return given()
                .body(booking)
                .contentType(ContentType.JSON)
               .when()
                .post("/booking");
    }

}