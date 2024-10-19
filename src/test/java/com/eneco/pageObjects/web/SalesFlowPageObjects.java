package com.eneco.pageObjects.web;

import com.eneco.pageObjects.interfaces.SalesFlowPageObjectsInterface;
import com.eneco.utils.Helper;

public class SalesFlowPageObjects implements SalesFlowPageObjectsInterface {

    String informationXpath = "(//p[@class='c-ezqaqn sparky-text'])";

    @Override
    public void fillInPostcodeWithAndHouseNumberWith(String postcode, String houseNumber) {
        Helper.waitAndSendKeysByXpath("//*[@name='postalCode']", postcode);
        Helper.waitAndSendKeysByXpath("//*[@name='houseNumber']", houseNumber);
    }

    @Override
    public Boolean isAddressDisplayed() {
        return Helper.isElementDisplayedByXpath("//*[@data-scope='AddressFinder']//span[@class='c-ezqaqn c-ezqaqn-glukyz-size-BodyS sparky-text']");
    }

    @Override
    public void clickCalculateButton() {
        Helper.waitAndClickByXpath("//*[@data-label='Bereken je maandbedrag']");
    }

    @Override
    public void chooseElectricityOption() {
        Helper.waitAndClickByXpath("//*[@data-label='Alleen stroom']");
    }

    @Override
    public void clickNextButton() {
        Helper.waitAndClickByXpath("//*[@data-label='Volgende']");
    }

    @Override
    public void clickYesOnAnnualConsumption() {
        Helper.waitAndClickByXpath("//*[@data-label='Ja, ik vul mijn verbruik zelf in']");
    }

    @Override
    public void clickNoToSmartMeter() {
        Helper.waitAndClickByXpath("//*[@data-label='Nee, ik heb geen slimme meter']");
    }

    @Override
    public void enterAnnualElectricityConsumption(String consumption) {
        Helper.waitAndSendKeysByName("usageElectricityHigh", consumption);
    }

    @Override
    public void clickYesOnSolarPanel() {
        Helper.waitAndClickByXpath("//*[@data-label='Ja, ik wek zelf stroom op']");
    }

    @Override
    public void returnPowerBackToGrid(String power) {
        Helper.waitAndSendKeysByName("solarPanelsOutput", power);
    }

    @Override
    public void clickNoOnMoving() {
        Helper.waitAndClickByXpath("//*[@data-label='Nee, ik ga niet verhuizen']");
    }

    @Override
    public void chooseOffer() {
        Helper.waitAndClickByXpath("//*[@data-label='Vast 1 jaar stroom']");
    }

    @Override
    public void clickGoYourData() {
        Helper.waitAndClickByXpath("//*[@data-label='Naar je gegevens']");
    }

    @Override
    public String checkTheStartDate() {
        return Helper.getAttributeByName("deliveryDate", "value");
    }

    @Override
    public void clickYesOnLiveOrWorkAddress() {
        Helper.waitAndClickByXpath("//*[@data-label='Ja']");
    }

    @Override
    public void fillInPersonalInfo(String salutation, String name, String initials, String surName, String dateOfBirth) {
        Helper.scrollDownToBottomOfPage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (salutation.equals("Mr")) {
            Helper.waitAndClickByXpath("//*[@data-label='Dhr.']");
        } else if (salutation.equals("Mrs")) {
            Helper.waitAndClickByXpath("//*[@data-label='Mevr.']");
        }

        Helper.waitAndSendKeysByName("firstName", name);
        Helper.waitAndSendKeysByName("initials", initials);
        Helper.waitAndSendKeysByName("surname", surName);
        dateOfBirth = dateOfBirth.replace("-", "");
        String day = dateOfBirth.substring(0, 2);
        String month = dateOfBirth.substring(2, 4);
        String year = dateOfBirth.substring(4, 8);
        Helper.waitAndSendKeysByName("day", day);
        Helper.waitAndSendKeysByName("month", month);
        Helper.waitAndSendKeysByName("year", year);
    }

    @Override
    public void fillInPhoneNumberAndEmail(String phoneNumber, String email) {
        Helper.waitAndSendKeysByName("phoneNumber", phoneNumber);
        Helper.waitAndSendKeysByName("emailAddress", email);
    }

    @Override
    public void clickCheckYourDataButton() {
        Helper.scrollDownToBottomOfPage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Helper.waitAndClickByXpath("//*[@data-label='Controleer je bestelling']");
    }

    @Override
    public String getFullName() {
        return Helper.getTextOfWebElementByXpath(informationXpath + "[1]");
    }

    @Override
    public String getDateOfBirth() {
        return Helper.getTextOfWebElementByXpath(informationXpath + "[2]");
    }

    @Override
    public String getPhoneNumber() {
        return Helper.getTextOfWebElementByXpath(informationXpath + "[3]");
    }

    @Override
    public String getEmail() {
        return Helper.getTextOfWebElementByXpath(informationXpath + "[4]");
    }

    @Override
    public String getHouseNumber() {
        String fullAddress = Helper.getTextOfWebElementByXpath(informationXpath + "[5]");
        return fullAddress.substring(fullAddress.indexOf(' ') + 1);
    }

    @Override
    public String getPostCode() {
        String postCodeAndCity = Helper.getTextOfWebElementByXpath(informationXpath + "[6]");
        return postCodeAndCity.substring(0, postCodeAndCity.indexOf(' '));
    }

}
