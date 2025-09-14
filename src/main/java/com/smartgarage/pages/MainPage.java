package com.smartgarage.pages;

import com.testframework.PropertiesManager;
import com.testframework.core.BaseWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.cert.X509Certificate;

public class MainPage extends BaseWebPage {

    @FindBy(xpath = "//i[@class='fas fa-sign-in-alt']")
    private WebElement loginButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/\"]")
    private WebElement homeButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/services\"]")
    private WebElement servicesButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/vehicles\"]")
    private WebElement vehiclesButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/gallery\"]")
    private WebElement galleryButton;

    @FindBy(xpath = "//ul[@class='sf-menu']//a[@href=\"/contact\"]")
    private WebElement jobCenterButton;

    @FindBy(xpath = "//a[@title='MAKE AN APPOINTMENT']")
    private WebElement makeAnAppointmentButton;

    @FindBy(xpath = "")
    private WebElement readMoreButton;

    public MainPage(WebDriver webDriver) {
        super(webDriver, "");
        PageFactory.initElements(webDriver, this);
    }

    @Override
    protected String getPageUrl() {
        return super.getPageUrl();
    }
}
