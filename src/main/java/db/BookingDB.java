package db;

import model.Booking;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BookingDB {

    private Connection conn;

    public BookingDB() throws SQLException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:/Users/mark/Documents/restful-booker-java/booking.db");
        ds.setUser("sa");
        ds.setPassword("sa");
        conn = ds.getConnection();
    }

    public Booking create(Booking booking) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

        int updateSuccess = conn.prepareStatement(sql).executeUpdate();

        if(updateSuccess > 0){
            return query();
        } else {
            return null;
        }
    }

    public Booking query() throws SQLException{
        String sql = "SELECT * FROM bookings WHERE id='1'";

        ResultSet result = conn.prepareStatement(sql).executeQuery();
        result.next();

        return new Booking.BookingBuilder()
                          .setFirstname(result.getString("firstname"))
                          .setLastname(result.getString("lastname"))
                          .setTotalprice(result.getInt("totalprice"))
                          .setDepositpaid(result.getBoolean("deposit"))
                          .setCheckin(result.getDate("checkin"))
                          .setCheckout(result.getDate("checkout"))
                          .setAdditionalneeds(result.getString("additional"))
                          .build();
    }
}
