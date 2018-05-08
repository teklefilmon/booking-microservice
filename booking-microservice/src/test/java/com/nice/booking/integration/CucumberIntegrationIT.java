package com.nice.booking.integration;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;

import com.nice.booking.BookingMicroServiceApplication;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/com.nice.booking.feature"}, format = {"pretty", "html:target/reports/cucumber/html",
        "json:target/cucumber.json", "usage:target/usage.jsonx", "junit:target/junit.xml"})
@Import(BookingMicroServiceApplication.class)
public class CucumberIntegrationIT {
}
