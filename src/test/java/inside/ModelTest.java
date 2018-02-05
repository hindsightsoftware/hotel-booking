package inside;

import db.BookingDB;
import model.Booking;
import model.CreatedBooking;
import org.approvaltests.Approvals;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ModelTest {

    @Before
    public void resetDatabase() throws SQLException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:/Users/mark/Documents/restful-booker-java/booking.db");
        ds.setUser("sa");
        ds.setPassword("sa");
        Connection conn = ds.getConnection();

        String sql = "ALTER TABLE bookings ALTER COLUMN id RESTART WITH 1";
        conn.prepareStatement(sql).execute();
    }

    private Date checkin = new Date();
    private Date checkout = new Date();

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
        CreatedBooking result = bookingDB.create(booking);

        Approvals.verify(result);
    }

    @Test
    public void testGetBooking() throws SQLException {
        checkin.setTime(1514764800);
        checkout.setTime(1514851200);

        BookingDB bookingDB = new BookingDB();
        Booking result = bookingDB.query(1);

        Approvals.verify(result);
    }

}
