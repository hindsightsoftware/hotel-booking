package db;

import model.Booking;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSql {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Date checkin;
    private Date checkout;
    private String additionalneeds;
    private SimpleDateFormat dateFormat;

    InsertSql(Booking booking) {
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
        return "INSERT INTO bookings(firstname, lastname, totalprice, deposit, checkin, checkout, additional) VALUES(" +
               "'" + firstname + "'," +
               "'" + lastname + "'," +
               totalprice + "," +
               depositpaid + "," +
               "'" + dateFormat.format(checkin) + "'," +
               "'" + dateFormat.format(checkout) + "'," +
                "'" + additionalneeds + "',);";
    }

}
