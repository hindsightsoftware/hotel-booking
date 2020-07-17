package com.hindsightsoftware.hotelbooking.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hindsightsoftware.hotelbooking.entities.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BookingModel {

    @JsonProperty(value = "firstname")
    private String firstName;
    @JsonProperty(value = "lastname")
    private String lastName;
    @JsonProperty(value = "totalprice")
    private int totalPrice;
    @JsonProperty(value = "depositpaid")
    private boolean depositPaid;
    @JsonProperty(value = "bookingdates")
    private BookingDatesModel bookingDates;
    @JsonProperty(value = "additionalneeds")
    private String additionalNeeds;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
    }

    public BookingDatesModel getBookingDates() {
        return bookingDates;
    }

    public void setBookingDates(BookingDatesModel bookingDates) {
        this.bookingDates = bookingDates;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }

    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
    }

    public static BookingModel from(Booking entity) {
        BookingDatesModel bookingDates = new BookingDatesModel();
        bookingDates.setCheckin(entity.getCheckIn());
        bookingDates.setCheckout(entity.getCheckOut());

        BookingModel model = new BookingModel();
        model.setAdditionalNeeds(entity.getAdditionalNeeds());
        model.setBookingDates(bookingDates);
        model.setDepositPaid(entity.isDepositPaid());
        model.setTotalPrice(entity.getTotalPrice());
        model.setLastName(entity.getLastName());
        model.setFirstName(entity.getFirstName());
        return model;
    }
}
