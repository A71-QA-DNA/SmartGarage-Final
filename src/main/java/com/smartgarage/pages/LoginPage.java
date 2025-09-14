package com.smartgarage.pages;

import com.testframework.core.BaseWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseWebPage {

    @FindBy(id = "login-tab")
    private WebElement loginTab;

    @FindBy(id = "login-username")
    private WebElement usernameField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@id='login-form']//button")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@href=\"/users/forgot-password\"]")
    private WebElement forgotPasswordButton;

    @FindBy(id = "register-tab")
    private WebElement registerTab;

    @FindBy(id = "reg-username")
    private WebElement regUsernameField;

    @FindBy(id = "reg-email")
    private WebElement regEmailField;

    @FindBy(id = "reg-first-name")
    private WebElement regFirstNameField;

    @FindBy(id = "reg-last-name")
    private WebElement regLastNameField;

    @FindBy(id = "reg-phone")
    private WebElement regPhoneField;

    @FindBy(xpath = "//div[@id='register-form']//button")
    private WebElement registerButton;

    protected LoginPage(WebDriver webDriver) {
        super(webDriver, "auth/login");
        PageFactory.initElements(webDriver, this);
    }

    public LoginPage enterUsername(String username) {
        waitForElementToBeVisible(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public HomePage clickLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
        return new HomePage(webDriver);
    }

    public HomePage loginWith(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
    }

    @Override
    protected String getPageUrl() {
        return super.getPageUrl();
    }
}
