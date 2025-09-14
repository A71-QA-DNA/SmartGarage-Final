package com.smartgarage.pages.sections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationSection extends BaseSection {

    public NavigationSection(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//i[@class='fas fa-sign-in-alt']")
    protected WebElement loginButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/\"]")
    protected WebElement homeButton;

    @FindBy(xpath = "//a[@href=\"/users/6/details\"]")
    protected WebElement myDetailsButton;

    @FindBy(xpath = "//a[@href=\"/users/6/orders\"]")
    protected WebElement myOrdersButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/services\"]")
    protected WebElement servicesButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/vehicles\"]")
    protected WebElement vehiclesButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/gallery\"]")
    protected WebElement galleryButton;

    @FindBy(xpath = "//a[@title=\"Admin Panel\"]")
    protected WebElement adminPanelButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/contact\"]")
    protected WebElement jobCenterButton;

    @FindBy(xpath = "//a[@title='MAKE AN APPOINTMENT']")
    protected WebElement makeAnAppointmentButton;

    @FindBy(xpath = "")
    protected WebElement readMoreButton;

    @FindBy(xpath = "//i[@class='fas fa-sign-out-alt']")
    protected WebElement logoutButton;
}
