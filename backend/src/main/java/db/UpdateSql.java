package db;

import model.Booking;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateSql {

    private int id;
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Date checkin;
    private Date checkout;
    private String additionalneeds;
    private SimpleDateFormat dateFormat;

    UpdateSql(int id, Booking booking) {
        this.id = id;
        this.firstname = booking.getFirstname();
        this.lastname = booking.getLastname();
        this.totalprice = booking.getTotalprice();
        this.depositpaid = booking.isDepositpaid();
        this.checkin = booking.getBookingDates().getCheckin();
        this.checkout = booking.getBookingDates().getCheckout();
        this.additionalneeds = booking.getAdditionalneeds();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public String buildSql(){
        return "UPDATE bookings SET "
                + "firstname='" + firstname + "',"
                + "lastname='" + lastname + "',"
                + "totalprice=" + totalprice + ","
                + "deposit=" + depositpaid + ","
                + "checkin='" + dateFormat.format(checkin) + "',"
                + "checkout='" + dateFormat.format(checkout) + "',"
                + "additional='" + additionalneeds + "' WHERE ID=" + id;
    }
}
