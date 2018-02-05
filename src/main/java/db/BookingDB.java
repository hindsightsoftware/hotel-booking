package db;

import model.Booking;
import model.CreatedBooking;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BookingDB {

    private Connection conn;
    private SimpleDateFormat dateFormat;

    public BookingDB() throws SQLException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:" + System.getProperty("user.dir") + "/booking.db");
        ds.setUser("sa");
        ds.setPassword("sa");
        conn = ds.getConnection();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public CreatedBooking create(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings(firstname, lastname, totalprice, deposit, checkin, checkout, additional) VALUES("
                     + "'" + booking.getFirstname() + "',"
                     + "'" + booking.getLastname() + "',"
                     + booking.getTotalprice() + ","
                     + booking.isDepositpaid() + ","
                     + "'" + dateFormat.format(booking.getBookingDates().getCheckin()) + "',"
                     + "'" + dateFormat.format(booking.getBookingDates().getCheckout()) + "',"
                     + "'" + booking.getAdditionalneeds() + "'"
                     + ")";

        int updateSuccess = conn.prepareStatement(sql).executeUpdate();

        if(updateSuccess > 0){
            ResultSet lastInsertId = conn.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            lastInsertId.next();

            String querySql = "SELECT * FROM bookings WHERE id='" + lastInsertId.getInt("LAST_INSERT_ID()") + "'";

            ResultSet result = conn.prepareStatement(querySql).executeQuery();
            result.next();

            Booking createdBooking = new Booking.BookingBuilder()
                                            .setFirstname(result.getString("firstname"))
                                            .setLastname(result.getString("lastname"))
                                            .setTotalprice(result.getInt("totalprice"))
                                            .setDepositpaid(result.getBoolean("deposit"))
                                            .setCheckin(result.getDate("checkin"))
                                            .setCheckout(result.getDate("checkout"))
                                            .setAdditionalneeds(result.getString("additional"))
                                            .build();

            return new CreatedBooking(result.getInt("id"), booking);
        } else {
            return null;
        }
    }

    public Booking query(int id) throws SQLException{
        String sql = "SELECT * FROM bookings WHERE id='" + id + "'";

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

    public Boolean delete(int bookingid) throws SQLException {
        String sql = "DELETE FROM bookings WHERE id='" + bookingid + "'";

        int resultSet = conn.prepareStatement(sql).executeUpdate();
        return resultSet == 1;
    }

    public Boolean update(int bookingid, Booking newBooking) throws SQLException {
        String sql = "UPDATE bookings SET "
                        + "firstname='" + newBooking.getFirstname() + "',"
                        + "lastname='" + newBooking.getLastname() + "',"
                        + "totalprice=" + newBooking.getTotalprice() + ","
                        + "deposit=" + newBooking.isDepositpaid() + ","
                        + "checkin='" + dateFormat.format(newBooking.getBookingDates().getCheckin()) + "',"
                        + "checkout='" + dateFormat.format(newBooking.getBookingDates().getCheckout()) + "',"
                        + "additional='" + newBooking.getAdditionalneeds() + "' WHERE ID=" + bookingid;

        System.out.println(sql);

        int resultSet = conn.prepareStatement(sql).executeUpdate();
        return resultSet == 1;
    }
}
