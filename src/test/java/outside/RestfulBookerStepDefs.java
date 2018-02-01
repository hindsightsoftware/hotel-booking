package outside;

import api.Application;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Booking;
import org.approvaltests.Approvals;
import org.junit.Assert;
import org.springframework.boot.SpringApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class RestfulBookerStepDefs {

    private Booking booking;
    private SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
    private Response httpResponse;

    @Before
    public static void setup() {
        SpringApplication.run(Application.class);
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
        Booking responseBooking = httpResponse.body().as(Booking.class);
        String expectedResponse = "model.Booking{firstname='Mark'\n" +
                ", lastname='Winteringham'\n" +
                ", totalprice=111\n" +
                ", depositpaid=true\n" +
                ", bookingDates=\n" +
                "model.BookingDates{\n" +
                "checkin=Sun Jan 18 01:00:00 GMT 1970\n" +
                ", checkout=Sun Jan 18 01:00:00 GMT 1970}\n" +
                ", additionalneeds='Breakfast'\n" +
                "}";

        Assert.assertThat(expectedResponse,is(responseBooking.toString()));
    }

}
