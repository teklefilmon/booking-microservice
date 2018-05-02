package com.nice.booking;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.nice.booking.model.Booking;
import com.nice.car.model.Car;
import com.nice.customer.domain.Customer;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = { "qa" })
public class BookingMicroserviceIT
{
    @Value("${application.cars-microservice.host}")
    private String carsMicroserviceHost;
    @Value("${application.cars-microservice.port}")
    private int carsMicroservicePort;

    @Value("${application.customers-microservice.host}")
    private String customersMicroserviceHost;
    @Value("${application.customers-microservice.port}")
    private int customersMicroservicePort;

    @Value("${application.booking-microservice.port}")
    private int bookingMicroservicePort;

    private static String accessToken;

    @BeforeClass
    public static void login()
    {
        Map<String, String> params = new HashMap<>();
        params.put("username", "user1");
        params.put("password", "pass1");
        params.put("grant_type", "password");
        params.put("scope", "read write");

        String response = given().params(params).auth().preemptive().basic("booking-microservice", "secret").when()
                .post("http://localhost:9000/services/oauth/token").asString();

        JsonPath jsonPath = new JsonPath(response);
        accessToken = jsonPath.getString("access_token");
    }

    @Test
    public void givenCustomerIdAndPlateNumberShouldSaveBooking()
    {

        String randomString = RandomStringUtils.randomAlphanumeric(10);

        Customer customer = createCustomer(randomString);
        saveCustomer(customer);

        Car car = createCar(randomString);
        saveCar(car);

        Booking booking = new Booking(car.getPlateNumber(), customer.getUserName(), 7);
        saveBooking(booking);
    }

    /**
     * @param booking
     * @since May 1, 2018
     * @author fghebremariam
     */
    private void saveBooking(Booking booking)
    {
        given().auth().oauth2(accessToken).baseUri("http://localhost").port(bookingMicroservicePort).contentType(ContentType.JSON)
                .accept(ContentType.JSON).request().body(booking).when().post("/bookings").then().statusCode(HttpStatus.CREATED.value());
    }

    /**
     * @param car
     * @since May 1, 2018
     * @author fghebremariam
     */
    private void saveCar(Car car)
    {
        given().baseUri(carsMicroserviceHost).port(carsMicroservicePort).contentType(ContentType.JSON).accept(ContentType.JSON).request().body(car)
                .when().post("/cars").then().statusCode(HttpStatus.CREATED.value());
    }

    /**
     * @param customer
     * @since May 1, 2018
     * @author fghebremariam
     */
    private void saveCustomer(Customer customer)
    {
        given()

                .baseUri(customersMicroserviceHost).port(customersMicroservicePort).contentType(ContentType.JSON).accept(ContentType.JSON).request()
                .body(customer).when().post("/customers").then().statusCode(HttpStatus.CREATED.value());
    }

    private Car createCar(String randomString)
    {
        String type = "SUV-E2ETest" + randomString;
        String model = "2018-E2ETest" + randomString;
        BigDecimal dailyPrice = new BigDecimal(5);
        String plateNumber = "TX-5643-E2ETest" + randomString;

        return new Car(type, model, dailyPrice, plateNumber);
    }

    private Customer createCustomer(String randomString)
    {
        String firstName = "David-E2ETest" + randomString;
        String lastName = "John-E2ETest" + randomString;
        String email = "john.david-E2ETest@gmail.com" + randomString;
        String userName = "john.david-E2ETest" + randomString;
        return new Customer(firstName, lastName, email, userName);
    }

}
