package db;

import model.Booking;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BookingDB {

    public int create(Booking booking) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:/Users/mark/Documents/restful-booker-java/booking.db");
        ds.setUser("sa");
        ds.setPassword("sa");
        Connection conn = ds.getConnection();

        System.out.println(booking.toString());

        String sql = "INSERT INTO bookings(firstname, lastname, totalprice, deposit, checkin, checkout, additional) VALUES("
                     + "'" + booking.getFirstname() + "',"
                     + "'" + booking.getLastname() + "',"
                     + booking.getTotalprice() + ","
                     + booking.isDepositpaid() + ","
                     + "'" + dateFormat.format(booking.getBookingDates().getCheckin()) + "',"
                     + "'" + dateFormat.format(booking.getBookingDates().getCheckout()) + "',"
                     + "'" + booking.getAdditionalneeds() + "'"
                     + ")";

        System.out.println(sql);

        return conn.prepareStatement(sql).executeUpdate();
    }
}
