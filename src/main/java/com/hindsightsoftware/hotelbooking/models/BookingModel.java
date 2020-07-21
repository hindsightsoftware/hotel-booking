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

    public static class BookingBuilder {
        private String firstName;
        private String lastName;
        private int totalPrice;
        private boolean depositPaid;
        private Date checkIn;
        private Date checkOut;
        private String additionalNeeds;

        public BookingBuilder() {
            this.additionalNeeds = "";
            this.checkIn = new Date();
            this.checkOut = new Date();
            this.depositPaid = false;
            this.totalPrice = 0;
        }

        public BookingBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public BookingBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public BookingBuilder setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public BookingBuilder setDepositPaid(boolean depositPaid) {
            this.depositPaid = depositPaid;
            return this;
        }

        public BookingBuilder setCheckIn(Date checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public BookingBuilder setCheckOut(Date checkOut) {
            this.checkOut = checkOut;
            return this;
        }

        public BookingBuilder setAdditionalNeeds(String additionalNeeds) {
            this.additionalNeeds = additionalNeeds;
            return this;
        }

        public BookingModel build(){
            BookingDatesModel bookingDates = new BookingDatesModel();
            bookingDates.setCheckin(checkIn);
            bookingDates.setCheckout(checkOut);

            BookingModel booking = new BookingModel();
            booking.setTotalPrice(totalPrice);
            booking.setFirstName(firstName);
            booking.setLastName(lastName);
            booking.setDepositPaid(depositPaid);
            booking.setBookingDates(bookingDates);
            booking.setAdditionalNeeds(additionalNeeds);
            return booking;
        }
    }
}
