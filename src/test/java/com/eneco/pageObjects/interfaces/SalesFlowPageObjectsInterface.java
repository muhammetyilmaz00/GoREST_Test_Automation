package com.eneco.pageObjects.interfaces;

public interface SalesFlowPageObjectsInterface {
    void fillInPostcodeWithAndHouseNumberWith(String postcode, String houseNumber);

    Boolean isAddressDisplayed();

    void clickCalculateButton();

    void chooseElectricityOption();

    void clickNextButton();

    void enterAnnualConsumption(String decision);

    void chooseSmartMeterOption(String decision);

    void enterAnnualElectricityConsumption(String annualConsumption);

    void chooseSolarPanelOption(String decision);

    void returnPowerBackToGrid(String power);

    void chooseMovingOption(String decision);

    void chooseOffer();

    void clickGoYourData();

    String checkTheStartDate();

    void chooseLiveOrWorkAddressOption(String isLiveOrWorkAddress);

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
