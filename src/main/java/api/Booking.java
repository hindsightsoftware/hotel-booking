package api;

import model.BookingModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Booking {

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public BookingModel returnBooking() {
        Date checkin = new Date();
        checkin.setTime(1514764800);
        Date checkout = new Date();
        checkout.setTime(1514851200);

        BookingModel booking = new BookingModel("Mark", "Winteringham", 111, true, checkin, checkout, "Breakfast");

        return booking;
    }

}
