import java.util.Date;

public class Booking {

    private String firstname;
    private String lastname;
    private int price;
    private boolean booked;
    private BookingDates bookingDates;
    private String additional;

    public Booking(String firstname, String lastname, int price, boolean booked, Date checkin, Date checkout, String additional) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.price = price;
        this.booked = booked;
        this.bookingDates = new BookingDates(checkin, checkout);
        this.additional = additional;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "firstname='" + firstname + '\'' +
                "\n, lastname='" + lastname + '\'' +
                "\n, price=" + price +
                "\n, booked=" + booked +
                "\n, bookingDates=" + bookingDates.toString() +
                "\n, additional='" + additional + '\'' +
                "\n}";
    }
}
