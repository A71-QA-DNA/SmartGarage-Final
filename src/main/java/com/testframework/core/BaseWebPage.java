package com.testframework.core;

import com.testframework.PropertiesManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseWebPage {

    protected final WebDriver webDriver;
    private final String pageUrl;
    protected final WebDriverWait webDriverWait;

    protected BaseWebPage(WebDriver webDriver, String pageUrl) {
        this.webDriver = webDriver;
        this.pageUrl = pageUrl;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(PropertiesManager.getConfigProperties().getProperty("defaultTimeoutSeconds"))));
        PageFactory.initElements(webDriver, this);
    }

    protected BaseWebPage(WebDriver webDriver) {
        this(webDriver, "");
    }

    protected void waitForElementToBeVisible(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForElementToBeClickable(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    @FindBy(xpath = "//a[@title='CarService']")
    protected WebElement mainHeading;

    protected String getPageUrl() {
        return PropertiesManager.getConfigProperties().getProperty("smartGarageUrl") + pageUrl;
    }

    public void open() {
        webDriver.navigate().to(getPageUrl());
        waitForElementToBeVisible(mainHeading);
    }
//    private final String pageUrl;
//
//    // Url
//
//    public abstract String getBasePageUrl();
//
//    public BaseWebPage(String pageSpecificUrl) {
//        pageUrl = pageSpecificUrl;
//    }
//
//    protected Driver driver() {
//        return DriverManager.getDriver();
//    }
//
//    protected WebDriverWait driverWait() {
//        return driver().getDriverWait();
//    }
//
//    public String getPageUrl() {
//        return getBasePageUrl() + pageUrl;
//    }
//
//    // Actions
//    public void navigate() {
//        driver().get(getPageUrl());
//    }
//
//    public void assertNavigated() {
//        Assertions.assertEquals(getPageUrl(), driver().getCurrentUrl(), "Page was not navigated.");
//    }
}