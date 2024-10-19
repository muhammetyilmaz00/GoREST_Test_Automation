package com.eneco.stepDefinitions;

import com.eneco.pageObjects.web.SalesFlowPageObjects;
import com.eneco.utils.Helper;
import com.eneco.utils.LogUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;

public class SalesFlowStepDef {

    SalesFlowPageObjects salesFlow = new SalesFlowPageObjects();
    String salutation = "Mr";
    String name = "Peter";
    String initials = "PP";
    String surName = "Peet";
    String dateOfBirth = "10-10-1970";
    String phoneNumber = "0612345678";
    String email = "test@example.com";
    String postcode;
    String houseNumber;

    @When("I fill in postcode with {string} and houseNumber with {string}")
    public void iFillInPostcodeWithAndHouseNumberWith(String postcode, String houseNumber) {
        this.postcode = postcode;
        this.houseNumber = houseNumber;
        LogUtils.info("I fill in postcode with " + postcode + " and huisNummer with " + houseNumber);
        salesFlow.fillInPostcodeWithAndHouseNumberWith(postcode, houseNumber);
        Assert.assertTrue(salesFlow.isAddressDisplayed());
    }


    @And("I click the Bereken maandbedrag button")
    public void iClickTheBerekenMaandbedragButton() {
        LogUtils.info("I click the Bereken maandbedrag button");
        salesFlow.clickCalculateButton();
    }

    @And("I complete the calculation step")
    public void iCompleteTheCalculationStep() {
        LogUtils.info("I choose the electricity option");
        salesFlow.chooseElectricityOption();

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

        LogUtils.info("I click the yes button on annual consumption");
        salesFlow.clickYesOnAnnualConsumption();

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

        LogUtils.info("I click the no button on smart meter");
        salesFlow.clickYesOnAnnualConsumption();

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

        LogUtils.info("I click the no button on smart meter");
        salesFlow.clickNoToSmartMeter();

        LogUtils.info("I enter annual electricity consumption with 1300");
        salesFlow.enterAnnualElectricityConsumption("1300");

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

        LogUtils.info("I click the yes button on solar panel");
        salesFlow.clickYesOnSolarPanel();

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

        LogUtils.info("I return power back to grid with 100");
        salesFlow.returnPowerBackToGrid("100");

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

        LogUtils.info("I click the no button on moving");
        salesFlow.clickNoOnMoving();

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

    }

    @And("I choose the offer step")
    public void iChooseTheOfferStep() {
        LogUtils.info("I choose the offer");
        salesFlow.chooseOffer();

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

        LogUtils.info("I click the Naar je gegevens button");
        salesFlow.clickGoYourData();
    }

    @And("I complete the facts step")
    public void iCompleteTheFactsStep() {
        LogUtils.info("I check the start date");
        LocalDate today = LocalDate.now();
        Assert.assertTrue(Helper.compareDate(salesFlow.checkTheStartDate()).isAfter(today));

        LogUtils.info("I click the Volgende button");
        Helper.scrollDownToBottomOfPage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        salesFlow.clickNextButton();

        LogUtils.info("I click the yes button on live or work address");
        salesFlow.clickYesOnLiveOrWorkAddress();

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

        LogUtils.info("I fill in personal info");
        salesFlow.fillInPersonalInfo(salutation, name, initials, surName, dateOfBirth);

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();

        salesFlow.fillInPhoneNumberAndEmail(phoneNumber, email);

        LogUtils.info("I click the Controller je bestelling button");
        salesFlow.clickCheckYourDataButton();

    }

    @Then("I should see all the information on the control page")
    public void iShouldSeeAllTheInformationOnTheControlPage() {
        LogUtils.info("I should see all the information on the control page");
        Assert.assertEquals("Naam: " + name + " " + surName, salesFlow.getFullName());
        Assert.assertEquals("Geboortedatum: " + dateOfBirth, salesFlow.getDateOfBirth());
        Assert.assertEquals("Telefoon: " + phoneNumber, salesFlow.getPhoneNumber());
        Assert.assertEquals("E-mail: " + email, salesFlow.getEmail());
        Assert.assertEquals(houseNumber, salesFlow.getHouseNumber());
        Assert.assertEquals(postcode, salesFlow.getPostCode());
    }
}
