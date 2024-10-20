package com.eneco.utils;

import com.eneco.stepDefinitions.DeleteUserStepDef;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static io.restassured.RestAssured.reset;

public class Hooks {
    private static Scenario scenario;

    /**
     * It is executed before each test case which has the @UI tag
     * It navigates to the Eneco homepage and accepts the cookies
     */
    @Before(value = "@UI")
    public static void navigateToHomePage() {
        LogUtils.info("I navigate to Eneco homepage");
        Driver.getDriver().navigate().to(PropertiesFactory.getPropertyFromApplication("homepage"));
        try {
            LogUtils.info("I accept cookies");
            Helper.waitAndClick(By.xpath("//button[@data-label='Accepteren']"));
        } catch (Exception e) {
            LogUtils.info("Cookies popup not found!");
        }
    }

    /**
     * It is executed before each test case.
     * It also sets the current scenario, configures the logging properties, and logs the start of the scenario
     */
    @Before
    public static void init(Scenario scenario) {
        Hooks.scenario = scenario;
        Configurator.initialize(null, "src/test/resources/config/log4j2.xml");
        LogUtils.info("Started Scenario: " + scenario.getName());
    }

    /**
     * It is executed before each test case which has the @API tag.
     * It initializes the test environment by calling the setUp() method from the GoRestApiClient class.
     */
    @Before(value = "@API")
    public static void setupAPIClient() {
        GoRestApiClient.setUp();
    }

    /**
     * It takes a screenshot if the scenario fails and then closes the browser
     */
    @After(value = "@UI")
    public static void closeBrowser() {
        if (scenario.isFailed()) {
            final byte[] screenshot = (
                    (TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        Driver.closeDriver();
    }

    /**
     * It calls the deleteUser() method from the DeleteUserStepDef class
     */
    @After(value = "@createUser")
    public void deleteUser() {
        DeleteUserStepDef deleteUserStepDef = new DeleteUserStepDef();
        deleteUserStepDef.deleteUser();
    }

    /**
     * It resets the RestAssured values
     */
    @After(value = "@API")
    public static void resetValues() {
        reset();
    }

    /**
     * It logs an info message with the name of the scenario
     */
    @After
    public static void tearDown(Scenario scenario) {
        LogUtils.info("Finished Scenario: " + scenario.getName());
    }
}
