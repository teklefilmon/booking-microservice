package com.nice.booking.integration;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.nice.booking.BookingMicroServiceApplication;
import com.nice.booking.Properties;

import io.restassured.path.json.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = BookingMicroServiceApplication.class, loader = SpringBootContextLoader.class)
@ActiveProfiles(profiles = "${app.environment.name}")
public abstract class BaseIntegrationTest {
    @Autowired
    private Properties properties;

    protected String logIn(String usernameParam, String passwordParam){
        Assert.hasLength(usernameParam, "Username cannot be null or empty");
        Assert.hasLength(passwordParam, "Password cannot be null or empty");

        Map<String, String> params = new HashMap<>();
        params.put("username", usernameParam);
        params.put("password", passwordParam);
        params.put("grant_type", "password");
        params.put("scope", "read write");

        String response = given().params(params).auth().preemptive().basic("booking-microservice", "secret").when()
                .post(properties.getOauthUrl()).asString();

        JsonPath jsonPath = new JsonPath(response);
        return jsonPath.getString("access_token");
    }
}
