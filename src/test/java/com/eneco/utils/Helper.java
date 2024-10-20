package com.eneco.utils;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonParseException;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helper {

    private static final Duration durationTimeout = Duration.ofSeconds(10);

    public static void scrollIntoView(By locator) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollDownToBottomOfPage() {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
        javascriptExecutor.executeScript("window.scrollBy(0,document.documentElement.offsetHeight)");
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitAndClick(By locator) {
        scrollIntoView(locator);
        waitForElementToBeClickable(locator).click();
    }

    public static void waitAndSendKeys(By locator, String text) {
        scrollIntoView(locator);
        waitForElementToBeClickable(locator).sendKeys(text);
    }

    public static boolean isElementDisplayed(By locator) {
        try {
            return waitForElementToBeClickable(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static LocalDate convertDate(String date, String format) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static String getTextOfWebElement(By locator) {
        scrollIntoView(locator);
        return waitForElementToBeClickable(locator).getText();
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static JsonNode parseJson(String stringObject) {
        if (stringObject == null) {
            throw new NullPointerException("Input string is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readTree(stringObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
