package com.nice.booking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class Properties {
    @Value("${application.cars-microservice.host}")
    private String carsMSHost;
    @Value("${application.cars-microservice.port}")
    private int carsMSPort;
    @Value("${application.customers-microservice.host}")
    private String customersMSHost;
    @Value("${application.customers-microservice.port}")
    private int customersMSPort;
    @Value("${application.booking-microservice.port}")
    private int bookingMSPort;
    @Value("${application.oauth-microservice.url}")
    private String oauthUrl;
    @Value("${application.booking-microservice.host}")
    private String bookingMSHostName;

    public Properties() {
    }

    public Properties(String carsMSHostParam, int carsMSPortParam, String customersMSHostParam, int customersMSPortParam, int bookingMSPortParam, String bookingMSHostNameParam){
        this.carsMSHost = carsMSHostParam;
        this.carsMSPort = carsMSPortParam;
        this.customersMSHost = customersMSHostParam;
        this.customersMSPort = customersMSPortParam;
        this.bookingMSPort = bookingMSPortParam;
        this.bookingMSHostName = bookingMSHostNameParam;
    }

    public String getCarsMSHost() {
        return carsMSHost;
    }

    public int getCarsMSPort() {
        return carsMSPort;
    }

    public String getCustomersMSHost() {
        return customersMSHost;
    }

    public int getCustomersMSPort() {
        return customersMSPort;
    }

    public int getBookingMSPort() {
        return bookingMSPort;
    }

    public String getOauthUrl() {
        return oauthUrl;
    }

    public String getBookingMSHostName() {
        return bookingMSHostName;
    }
}