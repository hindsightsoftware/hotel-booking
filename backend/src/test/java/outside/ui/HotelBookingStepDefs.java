package outside.ui;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import outside.ui.driverfactory.DriverFactory;
import outside.ui.pageobjects.BookingPage;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class HotelBookingStepDefs {

    private WebDriver driver;
    private BookingPage bookingPage;

    @Before
    public void SetUp(){
        driver = new DriverFactory().create();
    }

    @After
    public void TearDown() {
        driver.quit();
    }

    @Given("^a user wants to make a booking with the following details$")
    public void navigateAndFillInForm(List<String> table) throws Exception {
        driver.navigate().to("http://localhost:8080/");

        createBooking(table);
    }

    @When("^the booking is submitted by the user$")
    public void submitBookingForm() throws Exception {
        bookingPage.waitForRowCount(2);
    }

    private List<WebElement> rowInsert;

    @Then("^the booking is successfully stored$")
    public void assertPageRow() throws Exception {
        rowInsert = bookingPage.getRows();
        Assert.assertThat(rowInsert.size(), is(2));
    }

    @Then("^shown to the user as stored$")
    public void assertRowEntry() throws Exception {
        String rowText = rowInsert.get(1).getText();

        Assert.assertThat(rowText, containsString("Mark"));
        Assert.assertThat(rowText, containsString("Winters"));
        Assert.assertThat(rowText, containsString("120"));
        Assert.assertThat(rowText, containsString("true"));
        Assert.assertThat(rowText, containsString("Breakfast"));
        Assert.assertThat(rowText, containsString("2018-01-01"));
        Assert.assertThat(rowText, containsString("2018-01-03"));
    }

    @Given("^Hotel Booking has existing bookings$")
    public void createNewBooking() throws Exception {
        driver.navigate().to("http://localhost:8080/");

        List<String> newBooking = new ArrayList<String>(){{
            add("James");
            add("Dean");
            add("999");
            add("true");
            add("1990-01-01");
            add("1990-01-31");
            add("Cake");
        }};

        createBooking(newBooking);
    }

    @When("^a specific booking is requested by the user$")
    public void waitForRowsToPopulate() throws Exception {
        bookingPage.waitForRowCount(2);
    }

    @Then("^the booking is shown$")
    public void assertBooking() throws Exception {
        rowInsert = bookingPage.getRows();
        String rowText = rowInsert.get(1).getText();

        Assert.assertThat(rowText, containsString("James"));
        Assert.assertThat(rowText, containsString("Dean"));
        Assert.assertThat(rowText, containsString("999"));
        Assert.assertThat(rowText, containsString("true"));
        Assert.assertThat(rowText, containsString("Cake"));
        Assert.assertThat(rowText, containsString("1990-01-01"));
        Assert.assertThat(rowText, containsString("1990-01-31"));
    }

    @When("^a specific booking is updated by the user$")
    public void updateASpecificBooking() throws Exception {
        bookingPage.waitForRowCount(2);
        bookingPage.clickEditBooking(1);
        bookingPage.setEditFirstname("Mark ==EDIT==");
        bookingPage.setEditLastname("Winteringham ==EDIT==");
        bookingPage.setEditPrice("888");
        bookingPage.setEditDeposit("false");
        bookingPage.setEditAdditional("Newspaper ==EDIT==");
        bookingPage.setEditCheckin("1983-01-01");
        bookingPage.setEditCheckout("1983-01-02");
        bookingPage.clickEditSubmit();
    }

    @Then("^the booking is shown to be updated$")
    public void assertUpdatedRow() throws Exception {
        driver.navigate().refresh();
        List<WebElement> updatedRowInsert = bookingPage.getRows();
        String rowText = updatedRowInsert.get(1).getText();

        Assert.assertThat(rowText, containsString("Mark ==EDIT=="));
        Assert.assertThat(rowText, containsString("Winteringham ==EDIT=="));
        Assert.assertThat(rowText, containsString("888"));
        Assert.assertThat(rowText, containsString("false"));
        Assert.assertThat(rowText, containsString("Newspaper ==EDIT=="));
        Assert.assertThat(rowText, containsString("1983-01-01"));
        Assert.assertThat(rowText, containsString("1983-01-02"));
    }

    @When("^a specific booking is deleted by the user$")
    public void deleteBooking() throws Exception {
        bookingPage.waitForRowCount(2);
        bookingPage.clickDeleteBooking(1);
    }

    @Then("^the booking is removed$")
    public void assertBookingIsDeleted() throws Exception {
        bookingPage.waitForRowCount(1);
        rowInsert = bookingPage.getRows();
        Assert.assertThat(rowInsert.size(), is(1));
    }

    private void createBooking(List<String> table){
        bookingPage = new BookingPage(driver);
        bookingPage.setFirstname(table.get(0));
        bookingPage.setLastname(table.get(1));
        bookingPage.setPrice(table.get(2));
        bookingPage.setDeposit(table.get(3));
        bookingPage.setAdditional(table.get(6));
        bookingPage.setCheckin(table.get(4));
        bookingPage.setCheckout(table.get(5));
        bookingPage.clickSubmit();
    }

}
