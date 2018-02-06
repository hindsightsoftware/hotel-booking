package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingID {

    @JsonProperty
    private int id;

    public BookingID(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookingID{" +
                "id=" + id +
                '}';
    }
}
