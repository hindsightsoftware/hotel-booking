package outside;

import api.Application;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Booking;
import org.springframework.boot.SpringApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class RestfulBookerStepDefs {

    private Booking booking;
    private SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
    private Response httpResponse;

    @Given("^a user wants to make a booking with the following details$")
    public void createBookingPayload(List<String> table) throws Exception {
        SpringApplication.run(Application.class);

        Date checkin = dateParser.parse(table.get(4));
        Date checkout = dateParser.parse(table.get(5));

        booking = new Booking(table.get(0), table.get(1), Integer.decode(table.get(2)), Boolean.getBoolean(table.get(3)), checkin, checkout, table.get(6));
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

}
