package com.eneco.pageObjects.web;

import com.eneco.pageObjects.interfaces.SalesFlowPageObjectsInterface;
import com.eneco.utils.Helper;
import org.openqa.selenium.By;

public class SalesFlowPageObjects implements SalesFlowPageObjectsInterface {

    String calculateButton = "[data-label='Bereken je maandbedrag']";
    String electricityOption = "[data-label='Alleen stroom']";
    String nextButton = "[data-label='Volgende']";
    String yesButtonOnAnnualConsumption = "[data-label='Ja, ik vul mijn verbruik zelf in']";
    String noButtonOnAnnualConsumption = "[data-label='Nee, help mij inschatten']";
    String yesButtonOnSmartMeter = "[data-label='Ja, ik heb een slimme meter']";
    String noButtonOnSmartMeter = "[data-label='Nee, ik heb geen slimme meter']";
    String yesButtonOnSolarPanel = "[data-label='Ja, ik wek zelf stroom op']";
    String noButtonOnSolarPanel = "[data-label='Nee, ik heb geen solarpanel']";
    String yesButtonOnMoving = "[data-label='Ja, ik ga verhuizen']";
    String noButtonOnMoving = "[data-label='Nee, ik ga niet verhuizen']";
    String chooseOffer = "[data-label='Vast 1 jaar stroom']";
    String goYourDataButton = "[data-label='Naar je gegevens']";
    String yesButtonOnLiveOrWorkAddress = "[data-label='Ja']";
    String noButtonOnLiveOrWorkAddress = "[data-label='Nee']";
    String checkYourDataButton = "[data-label='Controleer je bestelling']";
    String addressFinderXpath = "//*[@data-scope='AddressFinder']//span[@class='c-ezqaqn c-ezqaqn-glukyz-size-BodyS sparky-text']";
    String informationXpath = "(//p[@class='c-ezqaqn sparky-text'])";


    @Override
    public void fillInPostcodeWithAndHouseNumberWith(String postcode, String houseNumber) {
        Helper.waitAndSendKeys(By.name("postalCode"), postcode);
        Helper.waitAndSendKeys(By.name("houseNumber"), houseNumber);
    }

    @Override
    public Boolean isAddressDisplayed() {
        return Helper.isElementDisplayed(By.xpath(addressFinderXpath));
    }

    @Override
    public void clickCalculateButton() {
        Helper.waitAndClick(By.cssSelector(calculateButton));
    }

    @Override
    public void chooseElectricityOption() {
        Helper.waitAndClick(By.cssSelector(electricityOption));
    }

    @Override
    public void clickNextButton() {
        Helper.waitAndClick(By.cssSelector(nextButton));
    }

    @Override
    public void enterAnnualConsumption(String decision) {
        if (decision.equalsIgnoreCase("yes")) {
            Helper.waitAndClick(By.cssSelector(yesButtonOnAnnualConsumption));
        } else if (decision.equalsIgnoreCase("no")) {
            Helper.waitAndClick(By.cssSelector(noButtonOnAnnualConsumption));
        }
    }

    @Override
    public void chooseSmartMeterOption(String decision) {
        if (decision.equalsIgnoreCase("yes")) {
            Helper.waitAndClick(By.cssSelector(yesButtonOnSmartMeter));
        } else if (decision.equalsIgnoreCase("no")) {
            Helper.waitAndClick(By.cssSelector(noButtonOnSmartMeter));
        }
    }

    @Override
    public void enterAnnualElectricityConsumption(String consumption) {
        Helper.waitAndSendKeys(By.name("usageElectricityHigh"), consumption);
    }

    @Override
    public void chooseSolarPanelOption(String decision) {
        if (decision.equalsIgnoreCase("yes")) {
            Helper.waitAndClick(By.cssSelector(yesButtonOnSolarPanel));
        } else if (decision.equalsIgnoreCase("no")) {
            Helper.waitAndClick(By.cssSelector(noButtonOnSolarPanel));
        }
    }

    @Override
    public void returnPowerBackToGrid(String power) {
        Helper.waitAndSendKeys(By.name("solarPanelsOutput"), power);
    }

    @Override
    public void chooseMovingOption(String decision) {
        if (decision.equalsIgnoreCase("yes")) {
            Helper.waitAndClick(By.cssSelector(yesButtonOnMoving));
        } else if (decision.equalsIgnoreCase("no")) {
            Helper.waitAndClick(By.cssSelector(noButtonOnMoving));
        }
    }

    @Override
    public void chooseOffer() {
        Helper.waitAndClick(By.cssSelector(chooseOffer));
    }

    @Override
    public void clickGoYourData() {
        Helper.waitAndClick(By.cssSelector(goYourDataButton));
    }

    @Override
    public String checkTheStartDate() {
        return Helper.waitForElementToBeClickable(By.name("deliveryDate")).getAttribute("value");
    }

    @Override
    public void chooseLiveOrWorkAddressOption(String isLiveOrWorkAddress) {
        if (isLiveOrWorkAddress.equalsIgnoreCase("yes")) {
            Helper.waitAndClick(By.cssSelector(yesButtonOnLiveOrWorkAddress));
        } else if (isLiveOrWorkAddress.equalsIgnoreCase("no")) {
            Helper.waitAndClick(By.cssSelector(noButtonOnLiveOrWorkAddress));
        }
    }

    @Override
    public void fillInPersonalInfo(String salutation, String name, String initials, String surName, String dateOfBirth) {
        Helper.scrollDownToBottomOfPage();
        Helper.wait(1); // TODO: remove this

        if (salutation.equals("Dhr")) {
            Helper.waitAndClick(By.xpath("//*[@data-label='Dhr.']"));
        } else if (salutation.equals("Mevr")) {
            Helper.waitAndClick(By.xpath("//*[@data-label='Mevr.']"));
        }

        Helper.waitAndSendKeys(By.name("firstName"), name);
        Helper.waitAndSendKeys(By.name("initials"), initials);
        Helper.waitAndSendKeys(By.name("surname"), surName);
        dateOfBirth = dateOfBirth.replace("-", "");
        String day = dateOfBirth.substring(0, 2);
        String month = dateOfBirth.substring(2, 4);
        String year = dateOfBirth.substring(4, 8);
        Helper.waitAndSendKeys(By.name("day"), day);
        Helper.waitAndSendKeys(By.name("month"), month);
        Helper.waitAndSendKeys(By.name("year"), year);
    }

    @Override
    public void fillInPhoneNumberAndEmail(String phoneNumber, String email) {
        Helper.waitAndSendKeys(By.name("phoneNumber"), phoneNumber);
        Helper.waitAndSendKeys(By.name("emailAddress"), email);
    }

    @Override
    public void clickCheckYourDataButton() {
        Helper.scrollDownToBottomOfPage();
        Helper.wait(1); // TODO: remove this
        Helper.waitAndClick(By.cssSelector(checkYourDataButton));
    }

    @Override
    public String getFullName() {
        return Helper.getTextOfWebElement(By.xpath(informationXpath + "[1]"));
    }

    @Override
    public String getDateOfBirth() {
        return Helper.getTextOfWebElement(By.xpath(informationXpath + "[2]"));
    }

    @Override
    public String getPhoneNumber() {
        return Helper.getTextOfWebElement(By.xpath(informationXpath + "[3]"));
    }

    @Override
    public String getEmail() {
        return Helper.getTextOfWebElement(By.xpath(informationXpath + "[4]"));
    }

    @Override
    public String getHouseNumber() {
        String fullAddress = Helper.getTextOfWebElement(By.xpath(informationXpath + "[5]"));
        return fullAddress.substring(fullAddress.indexOf(' ') + 1);
    }

    @Override
    public String getPostCode() {
        String postCodeAndCity = Helper.getTextOfWebElement(By.xpath(informationXpath + "[6]"));
        return postCodeAndCity.substring(0, postCodeAndCity.indexOf(' '));
    }

}
