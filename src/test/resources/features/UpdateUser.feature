@regression @api @users @updateUserFeature
Feature: Update User
  As a user I want to update details of a user

  @updateUser @createUser
  Scenario: Update an existing user's details
    When I request to create a new user with the following details
      | name       | gender | email                  | status |
      | Alice Test | female | alice.test@example.com | active |
    And the response status code should be 201
    When I request to update the user with the following details
      | name       | gender | email                  | status |
      | Ali Tester | male   | ali.tester@example.com | active |
    Then the response status code should be 200
    And the response must contain the user details
      | name       | gender | email                  | status |
      | Ali Tester | male   | ali.tester@example.com | active |

  @partialUpdateUser @createUser
  Scenario: Partially update an existing user's details
    When I request to create a new user with the following details
      | name       | gender | email                  | status |
      | Alice Test | female | alice.test@example.com | active |
    And the response status code should be 201
    When I request to partially update the user with the following details
      | status   | gender |
      | inactive | male   |
    Then the response status code should be 200
    And the response must contain the user details
      | name       | gender | email                  | status   |
      | Alice Test | male   | alice.test@example.com | inactive |

  @updateUserInvalidId
  Scenario: Update user with invalid ID
    When I request to update a user with invalid id 123 and the following details
      | name       | gender | email                  | status |
      | Ali Tester | male   | ali.tester@example.com | active |
    Then the response status code should be 404
    And the response must contain an error message indicating "Resource not found"

  @partialUpdateUserInvalidValues @createUser
  Scenario: Partially update a user with invalid field values
    When I request to create a new user with the following details
      | name       | gender | email                  | status |
      | Alice Test | female | alice.test@example.com | active |
    And the response status code should be 201
    When I request to partially update the user with the following details
      | email        |
      | invalidEmail |
    Then the response status code should be 422
    And the response must contain an error message "is invalid" indicating field "email" is missing

  @updateUserWithoutAuth
  Scenario: Update user without authentication
    When I request to update a user with invalid id 12345 with no authentication
      | name       | gender | email                  | status |
      | Ali Tester | male   | ali.tester@example.com | active |
    Then the response status code should be 401
    And the response must contain an error message indicating "Invalid token"

  @updateUserMissingFields @createUser
  Scenario: Update user with missing required fields
    When I request to create a new user with the following details
      | name       | gender | email                  | status |
      | Alice Test | female | alice.test@example.com | active |
    And the response status code should be 201
    When I request to update the user with the following details
      | name |
      |      |
    Then the response status code should be 422
    And the response must contain an error message "can't be blank" indicating field "name" is missing
