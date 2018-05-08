Feature: Car rental booking

  Scenario: Book a car
    Given I am logged in to the booking service
    When A car is available
    Then I should be able to make a booking

#  Scenario: Cancel a booking
#    Given I am logged in to the booking service
#    When I have an existing booking
#    Then I should be able to cancel the booking
#
#  Scenario: Extend a booking
#    Given I am logged in to the booking service
#    When I have an existing booking
#    Then I should be able to extend the booking
