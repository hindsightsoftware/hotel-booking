import model.BookingModel;
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

        BookingModel bookingModel = new BookingModel("Mark", "Winteringham", 111, true, checkin, checkout, "Breakfast");

        Approvals.verify(bookingModel.toString());
    }

}
