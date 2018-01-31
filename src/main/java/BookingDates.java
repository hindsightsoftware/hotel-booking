import java.util.Date;

public class BookingDates {


    private Date checkin;
    private Date checkout;

    public BookingDates(Date checkin, Date checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "\nBookingDates{" +
                "\ncheckin=" + checkin +
                "\n, checkout=" + checkout +
                '}';
    }
}
