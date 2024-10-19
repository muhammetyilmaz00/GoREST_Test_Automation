@regression @api @users @deleteUserFeature
Feature: Delete User
  As a user I want to delete details of a user

  @deleteUser
  Scenario: Delete an existing user
    Given I request to create a new user with the following details
      | name      | gender | email                 | status |
      | Alex Test | male   | alex.test@example.com | active |
    And the response status code should be 201
    When I request to delete the user
    Then the response status code should be 204
    And the response must have no body content

  @deleteUserNonExistent
  Scenario: Delete a user that does not exist
    When I request to delete the user with id -1
    Then the response status code should be 404
    And the response must contain the error message "Resource not found"

  @deleteUserWithoutAuth @createUser
  Scenario: Attempt to delete a user without authentication
    Given I request to create a new user with the following details
      | name      | gender | email                 | status |
      | Alex Test | male   | alex.test@example.com | active |
    And the response status code should be 201
    When I request to delete the user without authentication
    Then the response status code should be 401
    And the response must contain the error message "Invalid token"
