package com.smartgarage.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseSmartGaragePage {

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

    public LoginPage() {
        super ("auth/login");
    }
}
