package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Booking {

    @JsonProperty
    private String firstname;
    @JsonProperty
    private String lastname;
    @JsonProperty
    private int totalprice;
    @JsonProperty
    private boolean depositpaid;
    @JsonProperty(value = "bookingdates")
    private BookingDates bookingDates;
    @JsonProperty
    private String additionalneeds;

    public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, Date checkin, Date checkout, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingDates = new BookingDates(checkin, checkout);
        this.additionalneeds = additionalneeds;
    }

    public Booking() {
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public BookingDates getBookingDates() {
        return bookingDates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public void setBookingDates(BookingDates bookingDates) {
        this.bookingDates = bookingDates;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return "model.Booking{" +
                "firstname='" + firstname + '\'' +
                "\n, lastname='" + lastname + '\'' +
                "\n, totalprice=" + totalprice +
                "\n, depositpaid=" + depositpaid +
                "\n, bookingDates=" + bookingDates.toString() +
                "\n, additionalneeds='" + additionalneeds + '\'' +
                "\n}";
    }
}
