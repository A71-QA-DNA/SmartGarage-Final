package com.testframework.core;

import com.testframework.Driver;
import com.testframework.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseWebPage {

    private final String pageUrl;

    protected BaseWebPage(String pageUrl) {
        this.pageUrl = pageUrl;
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(xpath = "//a[@title='CarService']")
    protected WebElement mainHeading;

    public abstract String getBasePageUrl();


    protected Driver driver() {
        return DriverManager.getDriver();
    }

    protected WebDriverWait driverWait() {
        return driver().getDriverWait();
    }

    public String getPageUrl() {
        return getBasePageUrl() + pageUrl;
    }

    public void navigate() {
        driver().get(getPageUrl());
    }

    protected void waitForElementToBeVisible(WebElement element) {
        driverWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        driverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void assertNavigated() {
        Assertions.assertEquals(getPageUrl(), driver().getCurrentUrl(), "Page was not navigated.");
    }
}