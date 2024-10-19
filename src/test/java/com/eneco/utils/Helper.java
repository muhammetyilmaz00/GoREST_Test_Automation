package com.eneco.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helper {

    private static final Duration durationTimeout = Duration.ofSeconds(10);

    public static void scrollIntoViewByXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor js = ((JavascriptExecutor) Driver.getDriver());
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollIntoViewById(String id) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        JavascriptExecutor js = ((JavascriptExecutor) Driver.getDriver());
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollIntoViewByName(String name) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
        JavascriptExecutor js = ((JavascriptExecutor) Driver.getDriver());
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollDownToBottomOfPage() {
        JavascriptExecutor js = ((JavascriptExecutor) Driver.getDriver());
        js.executeScript("window.scrollBy(0,document.documentElement.offsetHeight)");
    }

    public static void waitAndClickByXpath(String xpath) {
        scrollIntoViewByXpath(xpath);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        element.click();
    }

    public static void waitAndClickByID(String id) {
        scrollIntoViewById(id);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        element.click();
    }

    public static void waitAndSendKeysByXpath(String xpath, String text) {
        scrollIntoViewByXpath(xpath);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        element.sendKeys(text);
    }

    public static void waitAndSendKeysByName(String name, String text) {
        scrollIntoViewByName(name);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        element.sendKeys(text);
    }

    public static void waitAndSendKeysByID(String id, String text) {
        scrollIntoViewById(id);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        element.sendKeys(text);
    }

    public static boolean isElementDisplayedByXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element.isDisplayed();
    }

    public static void navigateURL(String URL) {
        Driver.getDriver().navigate().to(URL);
    }

    public static String getCurrentURL() {
        return Driver.getDriver().getCurrentUrl();
    }

    public static String getAttributeByName(String name, String attribute) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        return element.getAttribute(attribute);
    }

    public static LocalDate compareDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String getTextOfWebElementByXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationTimeout);
        scrollIntoViewByXpath(xpath);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element.getText();
    }

}
