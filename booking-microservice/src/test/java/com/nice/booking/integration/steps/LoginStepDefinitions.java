package com.nice.booking.integration.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

import java.util.Collections;
import java.util.List;

import com.nice.booking.integration.BaseIntegrationTest;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginStepDefinitions extends BaseIntegrationTest {
    private List<User> users = Collections.emptyList();

    @Given("^The following users$")
    public void retrieveUsers(DataTable userList) {
        users = userList.asList(User.class);
    }

    @Then("^I should login successfully$")
    public void tryToLogInUsingEachUser() {
        users.forEach(user -> {
            String accessToken = logIn(user.getUsername(), user.getPassword());
            assertThat(accessToken, not(isEmptyOrNullString()));
        });
    }

    @Then("^I should not get a token$")
    public void loginFailure(){
        users.forEach(user -> {
            String accessToken = logIn(user.getUsername(), user.getPassword());
            assertThat(accessToken, isEmptyOrNullString());
        });
    }

    private class User {
        private String username;
        private String password;
        public User(){}

        public User(String usernameParam, String passwordParam){
            this.username = usernameParam;
            this.password = passwordParam;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
