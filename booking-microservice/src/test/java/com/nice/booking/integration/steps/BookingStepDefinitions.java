package com.nice.booking.integration.steps;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.nice.booking.Properties;
import com.nice.booking.dto.Car;
import com.nice.booking.dto.Customer;
import com.nice.booking.integration.BaseIntegrationTest;
import com.nice.booking.model.Booking;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;


public class BookingStepDefinitions extends BaseIntegrationTest {
    private static final Logger logger = Logger.getLogger(BookingStepDefinitions.class);
    private static String accessToken;

    private Car car;
    private String randomString = randomAlphanumeric(10);

    @Autowired
    private Properties properties;

    @Given("^I am logged in to the booking service$")
    public void iAmLoggedInToBookingService() {
        if(StringUtils.isEmpty(accessToken)) {
            accessToken = logIn("user1", "pass1");
        }
        assertThat(accessToken, not(isEmptyOrNullString()));
        logger.info("Logged in successfully");
    }

    @And("^A car is available$")
    public void a_car_is_available() {
        car = createCar(randomString);
        saveCar(car);
    }

    @Then("^I should be able to make a booking$")
    public void i_should_be_able_to_make_a_booking() {
        Customer customer = createCustomer(randomString);
        saveCustomer(customer);

        Booking booking = new Booking(car.getPlateNumber(), customer.getUserName(), 7);
        saveBooking(booking);
    }

    private void saveBooking(Booking booking) {
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(accessToken)
            .baseUri(properties.getBookingMSHostName())
            .port(properties.getBookingMSPort())
            .request()
                .body(booking)
        .when()
            .post("/bookings")
        .then()
            .log().ifValidationFails()
            .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    private void saveCar(Car car) {
        given()
            .baseUri(properties.getCarsMSHost())
            .port(properties.getCarsMSPort())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .request()
                .body(car)
        .when()
            .post("/cars")
        .then()
            .log().ifValidationFails()
            .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }


    private void saveCustomer(Customer customer) {
        given()
            .baseUri(properties.getCustomersMSHost())
            .port(properties.getCustomersMSPort())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .request()
                .body(customer)
        .when()
            .post("/customers")
        .then()
            .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    private Car createCar(String randomString) {
        String type = "SUV-E2ETest" + randomString;
        String model = "2018-E2ETest" + randomString;
        BigDecimal dailyPrice = new BigDecimal(5);
        String plateNumber = "TX-5643-E2ETest" + randomString;

        return new Car(type, model, dailyPrice, plateNumber);
    }

    private Customer createCustomer(String randomString) {
        String firstName = "David-E2ETest" + randomString;
        String lastName = "John-E2ETest" + randomString;
        String email = "john.david-E2ETest@gmail.com" + randomString;
        String userName = "john.david-E2ETest" + randomString;
        return new Customer(firstName, lastName, email, userName);
    }
}
