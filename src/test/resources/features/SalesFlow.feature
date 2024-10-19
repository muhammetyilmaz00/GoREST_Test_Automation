@salesFlow
Feature: Eneco Sales Flow Automation
  As a user of Eneco
  I want to calculate my monthly energy cost and go through the sales flow
  So that I can check energy options using my postcode

  @UI
  Scenario: Fill out energy form and go through the sales flow
    When I fill in postcode with "9713RD" and houseNumber with "63"
    And I click the Bereken maandbedrag button
    And I complete the calculation step
    And I choose the offer step
    And I complete the facts step
    Then I should see all the information on the control page

