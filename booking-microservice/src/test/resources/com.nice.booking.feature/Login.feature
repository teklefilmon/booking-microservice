Feature: Login

  Scenario: Successful Login
    Given The following users
      | username | password |
      | user1    | pass1    |
      | user2    | pass2    |
    Then I should login successfully

  Scenario: Unsuccessful Login
    Given The following users
      | username | password |
      | user3    | pass3    |
    Then I should not get a token