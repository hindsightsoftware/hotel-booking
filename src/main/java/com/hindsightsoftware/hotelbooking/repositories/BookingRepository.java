package com.hindsightsoftware.hotelbooking.repositories;

import com.hindsightsoftware.hotelbooking.entities.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
