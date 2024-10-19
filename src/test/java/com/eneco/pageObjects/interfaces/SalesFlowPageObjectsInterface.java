package com.eneco.pageObjects.interfaces;

public interface SalesFlowPageObjectsInterface {
    void fillInPostcodeWithAndHouseNumberWith(String postcode, String houseNumber);

    Boolean isAddressDisplayed();

    void clickCalculateButton();

    void chooseElectricityOption();

    void clickNextButton();

    void clickYesOnAnnualConsumption();

    void clickNoToSmartMeter();

    void enterAnnualElectricityConsumption(String annualConsumption);

    void clickYesOnSolarPanel();

    void returnPowerBackToGrid(String power);

    void clickNoOnMoving();

    void chooseOffer();

    void clickGoYourData();

    String checkTheStartDate();

    void clickYesOnLiveOrWorkAddress();

    void fillInPersonalInfo(String salutation, String name, String initials, String surName, String dateOfBirth);

    void fillInPhoneNumberAndEmail(String phoneNumber, String email);

    void clickCheckYourDataButton();

    String getFullName();

    String getDateOfBirth();

    String getPhoneNumber();

    String getEmail();

    String getHouseNumber();

    String getPostCode();
}
