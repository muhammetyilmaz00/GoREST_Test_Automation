package com.eneco.stepDefinitions;

import com.eneco.pageObjects.web.SalesFlowPageObjects;
import com.eneco.utils.*;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.network.Network;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;


public class SalesFlowStepDef {

    SalesFlowPageObjects salesFlow = new SalesFlowPageObjects();
    SoftAssertions softAssertions = new SoftAssertions();
    Response response;

    String shoppingBasketId = "";

    @When("I fill in postcode with {string} and houseNumber with {string}")
    public void iFillInPostcodeWithAndHouseNumberWith(String postcode, String houseNumber) {
        LogUtils.info("I fill in postcode with " + postcode + " and huisNummer with " + houseNumber);
        salesFlow.fillInPostcodeWithAndHouseNumberWith(postcode, houseNumber);
        Assert.assertTrue(salesFlow.isAddressDisplayed());
    }


    @And("I click the Bereken maandbedrag button")
    public void iClickTheBerekenMaandbedragButton() {
        LogUtils.info("I click the Bereken maandbedrag button");
        salesFlow.clickCalculateButton();
    }

    @And("I choose the electricity option")
    public void iChooseTheElectricityOption() {
        LogUtils.info("I choose the electricity option");
        salesFlow.chooseElectricityOption();

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();
    }

    @And("I click the {string} button on annual consumption")
    public void iClickTheYesButtonOnAnnualConsumption(String decision) {
        LogUtils.info("I click the yes button on annual consumption");
        salesFlow.enterAnnualConsumption(decision);

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();
    }


    @And("I click the {string} button on smart meter")
    public void iClickTheNoButtonOnSmartMeter(String decision) {
        LogUtils.info("I click the no button on smart meter");
        salesFlow.chooseSmartMeterOption(decision);
    }

    @And("I enter annual electricity consumption with {int}")
    public void iEnterAnnualElectricityConsumptionWith(int consumption) {
        LogUtils.info("I enter annual electricity consumption with 1300");
        salesFlow.enterAnnualElectricityConsumption(consumption + "");

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();
    }

    @And("I click the {string} button on solar panel")
    public void iClickTheYesButtonOnSolarPanel(String decision) {
        LogUtils.info("I click the yes button on solar panel");
        salesFlow.chooseSolarPanelOption(decision);

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();
    }

    @And("I return power back to grid with {int}")
    public void iReturnPowerBackToGridWith(int power) {
        LogUtils.info("I return power back to grid with 100");
        salesFlow.returnPowerBackToGrid(power + "");

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();
    }

    @And("I click the {string} button on moving")
    public void iClickTheNoButtonOnMoving(String decision) {
        LogUtils.info("I click the no button on moving");
        salesFlow.chooseMovingOption(decision);

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

    @And("I check the start date")
    public void iCompleteTheFactsStep() {
        LogUtils.info("I check the start date");
        LocalDate today = LocalDate.now();
        Assert.assertTrue(Helper.convertDate(salesFlow.checkTheStartDate(), "dd-MM-yyyy").isAfter(today));

        LogUtils.info("I click the Volgende button");
        Helper.scrollDownToBottomOfPage();
        Helper.wait(1); // TODO: improve this
        salesFlow.clickNextButton();
    }

    @And("I click the {string} button on live or work address")
    public void iClickTheYesButtonOnLiveOrWorkAddress(String isLiveOrWorkAddress) {
        LogUtils.info("I click the yes button on live or work address");
        salesFlow.chooseLiveOrWorkAddressOption(isLiveOrWorkAddress);

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();
    }


    @And("I fill in personal info as {string} {string} {string} {string} {string}")
    public void iFillInPersonalInfoAs(String salutation, String name, String initials, String surname, String birthdate) {
        LogUtils.info("I fill in personal info");
        salesFlow.fillInPersonalInfo(salutation, name, initials, surname, birthdate);

        LogUtils.info("I click the Volgende button");
        salesFlow.clickNextButton();
    }

    @And("I fill in contact info as {string} {string}")
    public void iFillInContactInfoAs(String phoneNumber, String email) {
        salesFlow.fillInPhoneNumberAndEmail(phoneNumber, email);

        LogUtils.info("I click the Controller je bestelling button");
        salesFlow.clickCheckYourDataButton();
    }

    @Then("I should see all the information on the control page")
    public void iShouldSeeAllTheInformationOnTheControlPage(DataTable dataTable) {
        Map<String, String> data = dataTable.transpose().asMap(String.class, String.class);

        LogUtils.info("I should see all the information on the control page");
        softAssertions.assertThat(salesFlow.getFullName())
                .as("Full name should be 'Naam: " + data.get("name") + " " + data.get("surname") + "'")
                .isEqualTo("Naam: " + data.get("name") + " " + data.get("surname"));
        softAssertions.assertThat(salesFlow.getDateOfBirth())
                .as("Date of birth should be 'Geboortedatum: " + data.get("birthdate") + "'")
                .isEqualTo("Geboortedatum: " + data.get("birthdate"));

        softAssertions.assertThat(salesFlow.getPhoneNumber())
                .as("Phone number should be 'Telefoon: " + data.get("phoneNumber") + "'")
                .isEqualTo("Telefoon: " + data.get("phoneNumber"));

        softAssertions.assertThat(salesFlow.getEmail())
                .as("Email should be 'E-mail: " + data.get("email") + "'")
                .isEqualTo("E-mail: " + data.get("email"));

        softAssertions.assertThat(salesFlow.getHouseNumber())
                .as("House number should match")
                .isEqualTo(data.get("houseNumber"));

        softAssertions.assertThat(salesFlow.getPostCode())
                .as("Postcode should match")
                .isEqualTo(data.get("postcode"));
        softAssertions.assertAll();
    }

    @And("I setup the dev tools to get network requests")
    public void iSetupTheDevToolsToGetNetworkRequests() {
        LogUtils.info("Setup dev tools to get network requests");
        setupDevTools();

        LogUtils.info("I refresh the page to get the new network requests");
        Driver.getDriver().navigate().refresh();

        LogUtils.info("I validate the URL");
        Assert.assertEquals(PropertiesFactory.getPropertyFromApplication("homepage") + "duurzame-energie/bestellen2/je-gegevens/controle-keuze/", Driver.getDriver().getCurrentUrl());

        Helper.wait(2); // TODO: improve this
    }

    @And("I send the API request to get the information")
    public void iSendTheAPIRequestToGetTheInformation() {
        LogUtils.info("I check the shopping basket ID");
        if (shoppingBasketId == null || shoppingBasketId.isEmpty()) {
            throw new RuntimeException("Shopping Basket ID was not captured. Please check the listener setup.");
        }

        LogUtils.info("I send the API request to get the information");
        String url = "https://api-digital.enecogroup.com/dxpweb/public/nl/eneco/shoppingbasket";
        response = RestAssured.given()
                .header("accept", "application/json")
                .header("apikey", "673b639d7383463e8d7958387590acca")
                .header("Host", "api-digital.enecogroup.com")
                .when()
                .get(url + "/{shoppingBasketId}", shoppingBasketId)
                .then()
                .statusCode(200)
                .extract().response();
    }

    @And("I validate the information via API")
    public void iValidateTheInformationViaAPI(DataTable dataTable) {
        Map<String, String> data = dataTable.transpose().asMap(String.class, String.class);
        LogUtils.info("I validate the API response");
        String flowStateStr = response.jsonPath().getString("data.attributes.flowState");
        JsonNode jsonNode = Helper.parseJson(flowStateStr);

        softAssertions.assertThat(jsonNode.get("postalCode").asText())
                .as("Postal code should match")
                .isEqualTo(data.get("postcode"));

        softAssertions.assertThat(jsonNode.get("houseNumber").asText())
                .as("House number should match")
                .isEqualTo(data.get("houseNumber"));

        softAssertions.assertThat(jsonNode.get("salutation").asText())
                .as("Salutation should match in lowercase")
                .isEqualTo(data.get("salutation").toLowerCase());

        softAssertions.assertThat(jsonNode.get("firstName").asText())
                .as("First name should match")
                .isEqualTo(data.get("name"));

        softAssertions.assertThat(jsonNode.get("surname").asText())
                .as("Surname should match")
                .isEqualTo(data.get("surname"));

        softAssertions.assertThat(jsonNode.get("birthdate").asText())
                .as("Birthdate should match")
                .isEqualTo(data.get("birthdate"));

        softAssertions.assertThat(jsonNode.get("phoneNumber").asText())
                .as("Phone number should match")
                .isEqualTo(data.get("phoneNumber"));

        softAssertions.assertThat(jsonNode.get("emailAddress").asText())
                .as("Email address should match")
                .isEqualTo(data.get("email"));

        softAssertions.assertAll();
    }

    public void setupDevTools() {
        WebDriver driver = Driver.getDriver();
        if (driver instanceof ChromeDriver) {
            LogUtils.info("Setting up DevTools");
            DevTools devTools = ((ChromeDriver) driver).getDevTools();
            devTools.createSession();

            LogUtils.info("Enabling Network tracking in DevTools");
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            LogUtils.info("Adding listener to intercept network requests");
            devTools.addListener(Network.requestWillBeSent(), request -> {
                String url = request.getRequest().getUrl();
                if (url.contains("shoppingbasket")) {
                    LogUtils.info("Extracting shopping basket ID from URL: " + url);
                    String[] urlParts = url.split("/");
                    shoppingBasketId = urlParts[urlParts.length - 1];
                    LogUtils.info("Shopping basket ID: " + shoppingBasketId);
                }
            });
        }
    }

}
