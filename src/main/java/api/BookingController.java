package api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public String returnBooking() {
        return "to be wired";
    }

}
