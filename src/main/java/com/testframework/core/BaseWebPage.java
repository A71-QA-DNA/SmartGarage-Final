package com.testframework.core;

import com.smartgarage.pages.sections.NavigationSection;
import com.testframework.PropertiesManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseWebPage {

    protected final WebDriver webDriver;
    private final String pageUrl;
    protected final WebDriverWait webDriverWait;
    @Getter
    private final NavigationSection navigationSection;
    protected final Actions actions;

    protected BaseWebPage(WebDriver webDriver, String pageUrl) {
        this.webDriver = webDriver;
        this.pageUrl = pageUrl;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(PropertiesManager.getConfigProperties().getProperty("defaultTimeoutSeconds"))));
        this.navigationSection = new NavigationSection(webDriver);
        this.actions = new Actions(webDriver);
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

    protected void hoverOver(WebElement element, WebElement expectedElement) {
            waitForElementToBeVisible(element);
            actions.moveToElement(element).perform();
            waitForElementToBeClickable(expectedElement);
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