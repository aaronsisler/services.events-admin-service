Feature: Events
  Users need to do REST API operations for events

  Scenario: Retrieve an event that exists
    Given the client id is in the URL for events
    And an event does not exist in the database
    When a request is made to retrieve that specific event
    Then the correct response is returned