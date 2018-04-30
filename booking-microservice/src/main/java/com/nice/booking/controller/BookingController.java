package com.nice.booking.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nice.booking.model.Booking;
import com.nice.booking.service.BookingService;

@RestController
public class BookingController {

	private BookingService bookingService;

	public BookingController(BookingService service){
		this.bookingService = service;
	}
	
	@GetMapping(value = "/bookings")
	ResponseEntity<List<Booking>> getAllBookings() {
		return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
	}

	@PostMapping(value = "/bookings")
	ResponseEntity<Void> createBooking(@Valid @RequestBody Booking booking) {
		Booking newBooking = bookingService.create(booking);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBooking.getId())
				.toUri();
		responseHeaders.setLocation(newUserUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
}
