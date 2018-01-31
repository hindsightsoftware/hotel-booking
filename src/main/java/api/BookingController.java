package api;

import db.BookingDB;
import model.Booking;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class BookingController {

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public String returnBooking() {
        return "to be wired";
    }

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public int createBooking(@RequestBody Booking booking) throws SQLException {
        BookingDB bookingDB = new BookingDB();
        return bookingDB.create(booking);
    }

}
