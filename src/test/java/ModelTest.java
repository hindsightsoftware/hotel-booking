import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.Date;

public class ModelTest {

    @Test
    public void testModel(){
        Date checkin = new Date();
        checkin.setTime(1514764800);
        Date checkout = new Date();
        checkout.setTime(1514851200);

        Booking booking = new Booking("Mark", "Winteringham", 111, true, checkin, checkout, "Breakfast");

        Approvals.verify(booking.toString());
    }

}
