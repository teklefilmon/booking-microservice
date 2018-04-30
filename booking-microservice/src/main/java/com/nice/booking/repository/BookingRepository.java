package com.nice.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nice.booking.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
