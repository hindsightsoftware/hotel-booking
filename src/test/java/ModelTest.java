import db.BookingDB;
import model.Booking;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

public class ModelTest {

    private Date checkin = new Date();
    private Date checkout = new Date();

    @Test
    public void testModel(){
        checkin.setTime(1514764800);
        checkout.setTime(1514851200);

        Booking booking = new Booking("Mark", "Winteringham", 111, true, checkin, checkout, "Breakfast");

        Approvals.verify(booking.toString());
    }

    @Test
    public void testAddBooking() throws SQLException {
        checkin.setTime(1514764800);
        checkout.setTime(1514851200);

        Booking booking = new Booking("Mark", "Winteringham", 111, true, checkin, checkout, "Breakfast");

        BookingDB bookingDB = new BookingDB();
        int result = bookingDB.create(booking);

        Approvals.verify(result);
    }

}
