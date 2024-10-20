@salesFlow
Feature: Eneco Sales Flow Automation
  As a user of Eneco
  I want to calculate my monthly energy cost and go through the sales flow
  So that I can check energy options using my postcode

  @UI
  Scenario Outline: Fill out energy form and go through the sales flow
    When I fill in postcode with "<postcode>" and houseNumber with "<houseNumber>"
    And I click the Bereken maandbedrag button
    And I choose the electricity option
    And I click the "yes" button on annual consumption
    And I click the "no" button on smart meter
    And I enter annual electricity consumption with 1300
    And I click the "yes" button on solar panel
    And I return power back to grid with 100
    And I click the "no" button on moving
    And I choose the offer step
    And I check the start date
    And I click the "yes" button on live or work address
    And I fill in personal info as "<salutation>" "<name>" "<initials>" "<surname>" "<birthdate>"
    And I fill in contact info as "<phoneNumber>" "<email>"
    Then I should see all the information on the control page
      | name  | surname | birthdate  | phoneNumber | email            | postcode | houseNumber |
      | Peter | Peet    | 10-10-1970 | 0612345678  | test@example.com | 9713RD   | 63          |
    And I setup the dev tools to get network requests
    And I send the API request to get the information
    And I validate the information via API
      | name  | surname | salutation | birthdate  | phoneNumber | email            | postcode | houseNumber |
      | Peter | Peet    | Dhr        | 10-10-1970 | 0612345678  | test@example.com | 9713RD   | 63          |
    Examples:
      | postcode | houseNumber | salutation | name  | initials | surname | birthdate  | phoneNumber | email            |
      | 9713RD   | 63          | Dhr        | Peter | PP       | Peet    | 10-10-1970 | 0612345678  | test@example.com |
