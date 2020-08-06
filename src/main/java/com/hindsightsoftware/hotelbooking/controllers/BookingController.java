package com.hindsightsoftware.hotelbooking.controllers;

import com.hindsightsoftware.hotelbooking.entities.Booking;
import com.hindsightsoftware.hotelbooking.models.BookingModel;
import com.hindsightsoftware.hotelbooking.models.BookingIDModel;
import com.hindsightsoftware.hotelbooking.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.google.common.collect.Streams;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookingController {
    private BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;

        Booking booking = new Booking();
        booking.setFirstName("Mark");
        booking.setLastName("Winteringham");
        booking.setTotalPrice(123);
        booking.setDepositPaid(true);
        booking.setCheckIn(new Date());
        booking.setCheckOut(new Date());
        booking.setAdditionalNeeds("Breakfast");
        bookingRepository.save(booking);
    }

    @GetMapping("/api/booking")
    public List<BookingIDModel> getBookingIds() {
        return Streams.stream(bookingRepository.findAll()).map(booking -> {
            return new BookingIDModel(booking.getId());
        }).collect(Collectors.toList());
    }

    @PostMapping("/api/booking")
    public BookingIDModel createBooking(@RequestBody BookingModel model) {
        Booking booking = new Booking();
        booking.setFirstName(model.getFirstName());
        booking.setLastName(model.getLastName());
        booking.setAdditionalNeeds(model.getAdditionalNeeds());
        booking.setCheckIn(model.getBookingDates().getCheckin());
        booking.setCheckOut(model.getBookingDates().getCheckout());
        booking.setDepositPaid(model.isDepositPaid());
        booking.setTotalPrice(model.getTotalPrice());
        booking = bookingRepository.save(booking);

        return new BookingIDModel(booking.getId());
    }

    @GetMapping("/api/booking/{id}")
    public BookingModel getBookingById(@PathVariable Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (!optionalBooking.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }

        return BookingModel.from(optionalBooking.get());
    }

    @PutMapping("/api/booking/{id}")
    public BookingModel updateBooking(@RequestBody BookingModel model, @PathVariable Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (!optionalBooking.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }

        Booking booking = optionalBooking.get();
        booking.setFirstName(model.getFirstName());
        booking.setLastName(model.getLastName());
        booking.setAdditionalNeeds(model.getAdditionalNeeds());
        booking.setCheckIn(model.getBookingDates().getCheckin());
        booking.setCheckOut(model.getBookingDates().getCheckout());
        booking.setDepositPaid(model.isDepositPaid());
        booking.setTotalPrice(model.getTotalPrice());
        booking = bookingRepository.save(booking);

        return BookingModel.from(booking);
    }

    @DeleteMapping("/api/booking/{id}")
    public void deleteBooking(@PathVariable Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (!optionalBooking.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }

        bookingRepository.delete(optionalBooking.get());
    }
}
