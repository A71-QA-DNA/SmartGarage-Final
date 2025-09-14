//package com.smartgarage.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public abstract class BasePage {
//
//    protected final WebDriver webDriver;
//    protected final WebDriverWait webDriverWait;
//
//    protected BasePage(WebDriver webDriver) {
//        this.webDriver = webDriver;
//        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(20), Duration.ofSeconds(2));
//    }
//
//    protected void waitForPageToLoad() {
//        webDriverWait.until(ExpectedConditions.visibilityOf(mainHeading()));
//    }
//
//    protected abstract String getUrl();
//
//    public void open() {
//        webDriver.navigate().to(getUrl());
//    }
//
//    public WebElement mainHeading() {
//        String xpath = "//a[@title='CarService']";
//        return webDriver.findElement(By.xpath(xpath));
//    }
//}
