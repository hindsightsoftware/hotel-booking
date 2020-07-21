package com.hindsightsoftware.hotelbooking.utils;

import com.hindsightsoftware.hotelbooking.controllers.BookingController;
import com.hindsightsoftware.hotelbooking.models.BookingDatesModel;
import com.hindsightsoftware.hotelbooking.models.BookingIDModel;
import com.hindsightsoftware.hotelbooking.models.BookingModel;

import java.util.Date;

public class BookingBuilderMvc {
    final private BookingController bookingController;
    private String firstName;
    private String lastName;
    private int totalPrice;
    private boolean depositPaid;
    private Date checkIn;
    private Date checkOut;
    private String additionalNeeds;

    public BookingBuilderMvc(BookingController bookingController) {
        this.bookingController = bookingController;
        this.additionalNeeds = "";
        this.checkIn = new Date();
        this.checkOut = new Date();
        this.depositPaid = false;
        this.totalPrice = 0;
    }

    public BookingBuilderMvc setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BookingBuilderMvc setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BookingBuilderMvc setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public BookingBuilderMvc setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
        return this;
    }

    public BookingBuilderMvc setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public BookingBuilderMvc setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public BookingBuilderMvc setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
        return this;
    }

    public BookingIDModel build() {
        BookingDatesModel bookingDates = new BookingDatesModel();
        bookingDates.setCheckin(checkIn);
        bookingDates.setCheckout(checkOut);

        BookingModel model = new BookingModel();
        model.setTotalPrice(totalPrice);
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setDepositPaid(depositPaid);
        model.setBookingDates(bookingDates);
        model.setAdditionalNeeds(additionalNeeds);

        return bookingController.createBooking(model);
    }
}
