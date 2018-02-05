Feature: RestfulBooker

  Scenario: Create a booking in RestfulBooker
    Given a user wants to make a booking with the following details
      | Mark | Winters | 120 | true | 2018-01-01 | 2018-01-03 | Breakfast |
    When the booking is submitted by the user
    Then the booking is successfully stored
    And shown to the user as stored

  Scenario: Retrieve a booking in RestfulBooker
    Given RestfulBooker has existing bookings
    When a specific booking is requested by the user
    Then the booking is shown

  Scenario: Update a booking in RestfulBooker
    Given RestfulBooker has existing bookings
    And the user is authenticated
    When a specific booking is updated by the user
    Then the booking is shown

  Scenario: Delete a booking in RestfulBooker
    Given RestfulBooker has existing bookings
    And the user is authenticated
    When a specific booking is deleted by the user
    Then the booking is removed