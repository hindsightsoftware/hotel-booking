package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookingDates {

    @JsonProperty
    private Date checkin;
    @JsonProperty
    private Date checkout;

    public BookingDates(Date checkin, Date checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    @Override
    public String toString() {
        return "\nmodel.BookingDates{" +
                "\ncheckin=" + checkin +
                "\n, checkout=" + checkout +
                '}';
    }
}
