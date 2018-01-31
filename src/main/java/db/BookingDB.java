package db;

import model.BookingModel;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BookingDB {

    public int create(BookingModel bookingModel) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:/Users/mark/Documents/restful-booker-java/booking.db");
        ds.setUser("sa");
        ds.setPassword("sa");
        Connection conn = ds.getConnection();

        String sql = "INSERT INTO bookings(firstname, lastname, totalprice, deposit, checkin, checkout, additional) VALUES("
                     + "'" + bookingModel.getFirstname() + "',"
                     + "'" + bookingModel.getLastname() + "',"
                     + bookingModel.getPrice() + ","
                     + bookingModel.isBooked() + ","
                     + "'" + dateFormat.format(bookingModel.getBookingDates().getCheckin()) + "',"
                     + "'" + dateFormat.format(bookingModel.getBookingDates().getCheckout()) + "',"
                     + "'" + bookingModel.getAdditional() + "'"
                     + ")";

        System.out.println(sql);

        return conn.prepareStatement(sql).executeUpdate();
    }
}
