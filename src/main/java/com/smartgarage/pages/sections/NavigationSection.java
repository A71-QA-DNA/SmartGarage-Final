package com.smartgarage.pages.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationSection extends BaseSection {

    public NavigationSection(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement homeButton() {
        String xpath = "//a[@href='/'][@title='Home'][1]";
        return createByXpath(xpath);
    }

    public WebElement myDetailsButton() {
        String xpath = "";
        return createByXpath(xpath);
    }

    public WebElement myOrdersButton() {
        String xpath = "";
        return createByXpath(xpath);
    }

    public WebElement servicesButton() {
        String xpath = "";
        return createByXpath(xpath);
    }

    public WebElement vehiclesButton() {
        String xpath = "";
        return createByXpath(xpath);
    }

    public WebElement galleryButton() {
        String xpath = "";
        return createByXpath(xpath);
    }

    public WebElement adminPanelButton() {
        String xpath = "";
        return createByXpath(xpath);
    }

    public WebElement jobCenterButton() {
        String xpath = "";
        return createByXpath(xpath);
    }
}
