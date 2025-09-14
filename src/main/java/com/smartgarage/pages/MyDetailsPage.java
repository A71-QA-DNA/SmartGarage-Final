package com.smartgarage.pages;

import com.testframework.core.BaseWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyDetailsPage extends BaseWebPage {

    @FindBy(xpath = "//label[@for='avatarFile']")
    private WebElement choosePhotoButton;

    @FindBy(xpath = "//button[@id='edit-info-button']")
    private WebElement editInfoButton;

    @FindBy(xpath = "//input[@id='first-name-input']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='last-name-input']")
    private WebElement lastNameField;

    @FindBy(xpath = "//span[@id='username-text']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='email-input']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='phone-input']")
    private WebElement phoneField;

    @FindBy(xpath = "//span[@id='role-text']")
    private WebElement roleField;

    @FindBy(xpath = "//button[@id='save-info-button']")
    private WebElement saveInfoButton;

    @FindBy(xpath = "//a[@href='/users/6/password-change']")
    private WebElement changePasswordButton;

    @FindBy(xpath = "//input[@id='old-password']")
    private WebElement oldPasswordField;

    @FindBy(xpath = "//input[@id='new-password']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirm-password']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement savePasswordButton;

    protected MyDetailsPage(WebDriver webDriver) {
        super(webDriver, "users/6/details");
        PageFactory.initElements(webDriver, this);
    }
}
