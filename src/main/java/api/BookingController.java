package api;

import auth.Tokens;
import db.BookingDB;
import model.Booking;
import model.CreatedBooking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class BookingController {

    private BookingDB bookingDB;

    public BookingController() throws SQLException {
        bookingDB = new BookingDB();
    }

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public String readBooking() {
        return "to be wired";
    }

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public CreatedBooking createBooking(@RequestBody Booking booking) throws SQLException {
        return bookingDB.create(booking);
    }

    @RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
    public Booking getBooking(@PathVariable(value = "id") int id) throws SQLException {
        return bookingDB.query(id);
    }

    @RequestMapping(value = "/booking/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBooking(@PathVariable(value = "id") int id, @CookieValue(value ="token", required = false) String token) throws SQLException {
        if(Tokens.verify(token)){
            if(bookingDB.delete(id)){
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

}
