package com.eneco.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Locale;

public class Driver {

    /**
     * To close access to the object of this class from outside the class
     **/
    private Driver() {
    }

    /**
     * To close access from outside the class
     **/
    private static WebDriver driver;

    /**
     * Retrieves the WebDriver instance, creating a new one if it's null based on the browser type specified in the configuration.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {

        if (driver == null) {
            String browserType = PropertiesFactory.getPropertyFromApplication("browser").toLowerCase(Locale.ROOT);

            switch (browserType) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "chrome-headless":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    /**
     * Ensures that the driver value is set to null after calling the quit() method
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
