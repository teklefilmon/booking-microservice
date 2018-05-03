package com.nice.booking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nice.booking.dto.Car;
import com.nice.booking.dto.Customer;
import com.nice.booking.exception.CustomerNotFoundException;
import com.nice.booking.exception.EntityNotPersistedException;
import com.nice.booking.model.Booking;
import com.nice.booking.repository.BookingRepository;

@Service
@Transactional
public class BookingService
{

    private BookingRepository bookingRepository;

    @Value("${application.cars-microservice.host}")
    private String carsMicroserviceHost;
    @Value("${application.customers-microservice.host}")
    private String customersMicroserviceHost;

    @Value("${application.cars-microservice.port}")
    private int carsMicroservicePort;
    @Value("${application.customers-microservice.port}")
    private int customersMicroservicePort;

    public BookingService(BookingRepository repository)
    {
        this.bookingRepository = repository;
    }

    public Booking create(Booking booking)
    {
        String username = booking.getUsername();
        String plateNumber = booking.getPlateNumber();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Customer> customerEntity = template
                .getForEntity(customersMicroserviceHost + ":" + customersMicroservicePort + "/customers/" + username, Customer.class);
        if (HttpStatus.NOT_FOUND.equals(customerEntity.getStatusCode()))
        {
            throw new CustomerNotFoundException(String.format("Customer with username %s not found", username));
        }
        ResponseEntity<Car> carEntity = template.getForEntity(carsMicroserviceHost + ":" + carsMicroservicePort + "/cars/" + plateNumber, Car.class);
        if (HttpStatus.NOT_FOUND.equals(carEntity.getStatusCode()))
        {
            throw new CustomerNotFoundException(String.format("Car with plate number %s not found", plateNumber));
        }
        if (HttpStatus.OK.equals(customerEntity.getStatusCode()) && HttpStatus.OK.equals(carEntity.getStatusCode()))
        {
            return bookingRepository.save(booking);
        }
        throw new EntityNotPersistedException("Entity could not be persisted");
    }

    public List<Booking> getAllBookings()
    {
        return bookingRepository.findAll();
    }
}
