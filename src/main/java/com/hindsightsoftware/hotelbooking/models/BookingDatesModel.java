package com.hindsightsoftware.hotelbooking.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookingDatesModel {

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", locale = "en_GB", timezone="UTC")
    private Date checkin;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", locale = "en_GB", timezone="UTC")
    private Date checkout;

    public BookingDatesModel(Date checkin, Date checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesModel() {
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "\ncom.hindsightsoftware.hotelbooking.models.BookingDates{" +
                "\ncheckin=" + checkin +
                "\n, checkout=" + checkout +
                '}';
    }
}
