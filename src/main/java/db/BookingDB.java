package db;

import model.Booking;
import model.BookingID;
import model.CreatedBooking;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
        InsertSql insertSql = new InsertSql(booking);
        String sql = insertSql.buildSql();

        if(conn.prepareStatement(sql).executeUpdate() > 0){
            ResultSet lastInsertId = conn.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            lastInsertId.next();

            String querySql = "SELECT * FROM bookings WHERE id='" + lastInsertId.getInt("LAST_INSERT_ID()") + "'";

            ResultSet result = conn.prepareStatement(querySql).executeQuery();
            result.next();

            Booking createdBooking = new Booking(result);

            return new CreatedBooking(result.getInt("id"), createdBooking);
        } else {
            return null;
        }
    }

    public Booking query(int id) throws SQLException{
        String sql = "SELECT * FROM bookings WHERE id='" + id + "'";

        ResultSet result = conn.prepareStatement(sql).executeQuery();
        result.next();

        return new Booking(result);
    }

    public Boolean delete(int bookingid) throws SQLException {
        String sql = "DELETE FROM bookings WHERE id='" + bookingid + "'";

        int resultSet = conn.prepareStatement(sql).executeUpdate();
        return resultSet == 1;
    }

    public Boolean update(int bookingid, Booking newBooking) throws SQLException {
        UpdateSql updateSql = new UpdateSql(bookingid, newBooking);
        String sql = updateSql.buildSql();

        int resultSet = conn.prepareStatement(sql).executeUpdate();
        return resultSet == 1;
    }

    public List<BookingID> queryId() throws SQLException {
        List<BookingID> listToReturn = new ArrayList<BookingID>();
        String sql = "SELECT id FROM bookings";

        ResultSet results = conn.prepareStatement(sql).executeQuery();
        while(results.next()){
            listToReturn.add(new BookingID(results.getInt("id")));
        }

        return listToReturn;
    }
}
