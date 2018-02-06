package inside;

import db.BookingDB;
import model.Booking;
import model.BookingID;
import model.CreatedBooking;
import org.approvaltests.Approvals;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ModelTest {

    @Before
    public void resetDatabase() throws SQLException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:" + System.getProperty("user.dir") + "/booking.db");
        ds.setUser("sa");
        ds.setPassword("sa");
        Connection conn = ds.getConnection();

        conn.prepareStatement("DELETE FROM bookings").execute();
        conn.prepareStatement("ALTER TABLE bookings ALTER COLUMN id RESTART WITH 1").execute();
    }

    private Date checkin = new Date();
    private Date checkout = new Date();

    @Test
    public void testAddBooking() throws SQLException {
        CreatedBooking result = createBooking();

        Approvals.verify(result);
    }


    @Test
    public void testGetBooking() throws SQLException {
        CreatedBooking createdBooking = createBooking();

        BookingDB bookingDB = new BookingDB();
        Booking result = bookingDB.query(createdBooking.getBookingid());

        Approvals.verify(result);
    }

    @Test
    public void testDeletingBooking() throws SQLException {
        CreatedBooking createdBooking = createBooking();

        BookingDB bookingDB = new BookingDB();
        Boolean deletedBooking = bookingDB.delete(createdBooking.getBookingid());

        Approvals.verify(deletedBooking);
    }

    @Test
    public void testUpdateBooking() throws SQLException {
        CreatedBooking createdBooking = createBooking();

        Booking newBooking = new Booking.BookingBuilder()
                                        .setFirstname("Mark")
                                        .setLastname("Winteringham")
                                        .setTotalprice(123)
                                        .setDepositpaid(true)
                                        .setCheckin(checkin)
                                        .setCheckout(checkout)
                                        .setAdditionalneeds("Breakfast")
                                        .build();

        BookingDB bookingDB = new BookingDB();
        Boolean updatedBooking = bookingDB.update(createdBooking.getBookingid(), newBooking);

        Approvals.verify(updatedBooking);
    }

    @Test
    public void testQueryBookingIds() throws SQLException {
        createBooking();
        createBooking();

        BookingDB bookingDB = new BookingDB();
        List<BookingID> bookings = bookingDB.queryId();

        Approvals.verify(bookings.toString());
    }

    private CreatedBooking createBooking() throws SQLException {
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
        return bookingDB.create(booking);
    }
}
