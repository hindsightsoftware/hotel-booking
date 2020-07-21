package com.hindsightsoftware.hotelbooking.controllers;

import com.hindsightsoftware.hotelbooking.models.BookingIDModel;
import com.hindsightsoftware.hotelbooking.models.BookingModel;
import com.hindsightsoftware.hotelbooking.repositories.BookingRepository;
import com.hindsightsoftware.hotelbooking.utils.BookingBuilderMvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookingControllerTest {
    @Autowired
    private BookingRepository bookingRepository;
    private BookingController bookingController;

    @BeforeEach
    public void before() {
        bookingController = new BookingController(bookingRepository);
    }

    @Test
    public void createBooking() {
        BookingModel model = new BookingModel.BookingBuilder()
                .setFirstName("Mark")
                .setLastName("Winteringham")
                .setTotalPrice(123)
                .setDepositPaid(true)
                .setAdditionalNeeds("Breakfast")
                .build();

        BookingIDModel res = bookingController.createBooking(model);
        Assertions.assertNotNull(res);
        Assertions.assertTrue(res.getId() > 0);
    }

    @Test
    public void getBookings() {
        long booking0 = new BookingBuilderMvc(bookingController)
                .setFirstName("Mark")
                .setLastName("Winteringham")
                .setTotalPrice(100)
                .build()
                .getId();

        long booking1 = new BookingBuilderMvc(bookingController)
                .setFirstName("Mark2")
                .setLastName("Winteringham2")
                .setTotalPrice(200)
                .build()
                .getId();

        List<BookingIDModel> ids = bookingController.getBookingIds();
        Assertions.assertTrue(ids.size() >= 2);
        Assertions.assertTrue(ids.stream().anyMatch(i -> i.getId() == booking0));
        Assertions.assertTrue(ids.stream().anyMatch(i -> i.getId() == booking1));
    }

    @Test
    public void updateBooking() {
        long bookingId = new BookingBuilderMvc(bookingController)
                .setFirstName("Mark")
                .setLastName("Winteringham")
                .setTotalPrice(100)
                .build()
                .getId();

        BookingModel model = new BookingModel.BookingBuilder()
                .setFirstName("Mark")
                .setLastName("Winteringham")
                .setTotalPrice(200)
                .setDepositPaid(true)
                .setAdditionalNeeds("Breakfast")
                .build();

        BookingModel booking = bookingController.updateBooking(model, bookingId);

        Assertions.assertEquals(model.getTotalPrice(), booking.getTotalPrice());
        Assertions.assertEquals(model.getAdditionalNeeds(), booking.getAdditionalNeeds());
        Assertions.assertEquals(model.getFirstName(), booking.getFirstName());
        Assertions.assertEquals(model.getLastName(), booking.getLastName());
        Assertions.assertTrue(booking.isDepositPaid());
    }
}
