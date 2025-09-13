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
        String xpath = "//a[@href=\"/users/6/details\"]";
        return createByXpath(xpath);
    }

    public WebElement myOrdersButton() {
        String xpath = "//a[@href=\"/users/6/orders\"]";
        return createByXpath(xpath);
    }

    public WebElement servicesButton() {
        String xpath = "//a[@title=\"Services\"][1]";
        return createByXpath(xpath);
    }

    public WebElement vehiclesButton() {
        String xpath = "//a[@title=\"Vehicles\"]";
        return createByXpath(xpath);
    }

    public WebElement galleryButton() {
        String xpath = "//a[@title=\"Gallery\"][1]";
        return createByXpath(xpath);
    }

    public WebElement adminPanelButton() {
        String xpath = "//a[@title=\"Admin Panel\"]";
        return createByXpath(xpath);
    }

    public WebElement jobCenterButton() {
        String xpath = "//a[@title=\"Contact\"][1]";
        return createByXpath(xpath);
    }
}
