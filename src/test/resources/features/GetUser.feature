@regression @api @users @getUSerFeature
Feature: Get User
  As a user I want to get details of a user

  @getAllUsers
  Scenario: Get list of all users
    When I request to get all users
    Then the response status code should be 200
    And the response should match the schema of a list of users

  @getUserById @createUser
  Scenario: Get single user by ID
    When I request to create a new user with the following details
      | name     | gender | email               | status |
      | Jane Doe | female | janedoe@example.com | active |
    And the response status code should be 201
    And I request to get the newly created user with "id"
    Then the response status code should be 200
    And the response must contain the user details
      | name     | gender | email               | status |
      | Jane Doe | female | janedoe@example.com | active |

  @getUserByInvalidId
  Scenario: Get user by invalid ID
    When I request to get a user with id -1
    Then the response status code should be 404
    And the response must contain an error message indicating "Resource not found"

  @getUserByName @createUser
  Scenario: Get single user by name
    When I request to create a new user with the following details
      | name     | gender | email               | status |
      | Jane Doe | female | janedoe@example.com | active |
    And the response status code should be 201
    And I request to get the newly created user with "name"
    Then the response status code should be 200

  @getUsersWithPagination
  Scenario Outline: Get users with pagination
    When I request to get all users on page <page> with <perPage> users per page
    Then the response status code should be 200
    And the response must contain exactly <result> users
    And the response headers must contain pagination information <limit>
    Examples:
      | page | perPage | result | limit |
      | 1    | 10      | 10     | 10    |
      | 1    | 100     | 100    | 100   |
      | 1    | 101     | 10     | 10    |

  @getUserByIdWithoutAuth
  Scenario: Get user by ID without authentication
    When I request to get a user with id 12345 with no authentication
    Then the response status code should be 401
    And the response must contain an error message indicating "Invalid token"

  @getUserByNonNumericId
  Scenario: Get user by non-numeric ID
    When I request to get a user with a non-numeric id "xyz"
    Then the response status code should be 404
    And the response must contain an error message indicating "Resource not found"
