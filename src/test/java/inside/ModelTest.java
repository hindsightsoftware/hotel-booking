package inside;

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

        Booking booking = new Booking.BookingBuilder()
                .setFirstname("Mark")
                .setLastname("Winteringham")
                .setTotalprice(123)
                .setDepositpaid(true)
                .setCheckin(checkin)
                .setCheckout(checkout)
                .setAdditionalneeds("Breakfast")
                .build();

        Approvals.verify(booking.toString());
    }

    @Test
    public void testAddBooking() throws SQLException {
        checkin.setTime(1514764800);
        checkout.setTime(1514851200);

        Booking booking = new Booking.BookingBuilder()
                                     .setFirstname("Mark")
                                     .setLastname("Winteringham")
                                     .setTotalprice(123)
                                     .setDepositpaid(true)
                                     .setCheckin(checkin)
                                     .setCheckout(checkout)
                                     .setAdditionalneeds("Breakfast")
                                     .build();

        BookingDB bookingDB = new BookingDB();
        Booking result = bookingDB.create(booking);

        Approvals.verify(result);
    }

}
