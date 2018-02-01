Feature: RestfulBooker

  Scenario: Create a booking in RestfulBooker
    Given a user wants to make a booking with the following details
      | Mark | Winters | 120 | true | 2018-01-01 | 2018-01-03 | Breakfast |
    When the booking is submitted by the user
    Then the booking is successfully stored
    And shown to the user as stored