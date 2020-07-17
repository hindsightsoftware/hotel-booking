package com.hindsightsoftware.hotelbooking.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingIDModel {

    @JsonProperty
    private long id;

    public BookingIDModel(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookingID{" +
                "id=" + id +
                '}';
    }
}
