package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookingModel {

    @JsonProperty
    private String firstname;
    @JsonProperty
    private String lastname;
    @JsonProperty
    private int price;
    @JsonProperty
    private boolean booked;
    @JsonProperty
    private BookingDates bookingDates;
    @JsonProperty
    private String additional;

    public BookingModel(String firstname, String lastname, int price, boolean booked, Date checkin, Date checkout, String additional) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.price = price;
        this.booked = booked;
        this.bookingDates = new BookingDates(checkin, checkout);
        this.additional = additional;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }

    public BookingDates getBookingDates() {
        return bookingDates;
    }

    public String getAdditional() {
        return additional;
    }

    @Override
    public String toString() {
        return "model.BookingModel{" +
                "firstname='" + firstname + '\'' +
                "\n, lastname='" + lastname + '\'' +
                "\n, price=" + price +
                "\n, booked=" + booked +
                "\n, bookingDates=" + bookingDates.toString() +
                "\n, additional='" + additional + '\'' +
                "\n}";
    }
}
