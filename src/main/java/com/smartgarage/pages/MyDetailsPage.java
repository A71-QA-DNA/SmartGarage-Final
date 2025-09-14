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
    }

    public MyDetailsPage clickChoosePhoto() {
        waitForElementToBeClickable(choosePhotoButton);
        choosePhotoButton.click();
        return this;
    }

    public MyDetailsPage clickEditInfo() {
        waitForElementToBeClickable(editInfoButton);
        editInfoButton.click();
        return this;
    }

    public MyDetailsPage enterFirstName(String firstName) {
        waitForElementToBeVisible(firstNameField);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        return this;
    }

    public MyDetailsPage enterLastName(String lastName) {
        waitForElementToBeVisible(lastNameField);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        return this;
    }

    public MyDetailsPage enterEmail(String email) {
        waitForElementToBeVisible(emailField);
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public MyDetailsPage enterPhone(String phone) {
        waitForElementToBeVisible(phoneField);
        phoneField.clear();
        phoneField.sendKeys(phone);
        return this;
    }

    public MyDetailsPage clickSaveInfo() {
        waitForElementToBeClickable(saveInfoButton);
        saveInfoButton.click();
        return this;
    }

    public MyDetailsPage clickChangePassword() {
        waitForElementToBeClickable(changePasswordButton);
        changePasswordButton.click();
        return this;
    }

    public MyDetailsPage enterOldPassword(String oldPassword) {
        waitForElementToBeVisible(oldPasswordField);
        oldPasswordField.clear();
        oldPasswordField.sendKeys(oldPassword);
        return this;
    }

    public MyDetailsPage enterNewPassword(String newPassword) {
        waitForElementToBeVisible(newPasswordField);
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
        return this;
    }

    public MyDetailsPage enterConfirmPassword(String confirmPassword) {
        waitForElementToBeVisible(confirmPasswordField);
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
        return this;
    }

    public MyDetailsPage clickSavePassword() {
        waitForElementToBeClickable(savePasswordButton);
        savePasswordButton.click();
        return this;
    }

    public MyDetailsPage updateProfile(String firstName, String lastName, String email, String phone) {
        return clickEditInfo()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPhone(phone)
                .clickSaveInfo();
    }

    public MyDetailsPage changePassword(String oldPassword, String newPassword) {
        return clickChangePassword()
                .enterOldPassword(oldPassword)
                .enterNewPassword(newPassword)
                .enterConfirmPassword(newPassword)
                .clickSavePassword();
    }
}
