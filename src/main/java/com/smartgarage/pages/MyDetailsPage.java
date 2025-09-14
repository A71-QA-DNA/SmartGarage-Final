package com.smartgarage.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyDetailsPage extends BaseSmartGaragePage {

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

    protected MyDetailsPage() {
        super("");
    }

    public MyDetailsPage clickChoosePhoto() {
        choosePhotoButton.click();
        return this;
    }

    public MyDetailsPage clickEditInfo() {
        editInfoButton.click();
        return this;
    }

    public MyDetailsPage enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        return this;
    }

    public MyDetailsPage enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        return this;
    }

    public MyDetailsPage enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public MyDetailsPage enterPhone(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
        return this;
    }

    public MyDetailsPage clickSaveInfo() {
        saveInfoButton.click();
        return this;
    }

    public MyDetailsPage clickChangePassword() {
        changePasswordButton.click();
        return this;
    }

    public MyDetailsPage enterOldPassword(String oldPassword) {
        oldPasswordField.clear();
        oldPasswordField.sendKeys(oldPassword);
        return this;
    }

    public MyDetailsPage enterNewPassword(String newPassword) {
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
        return this;
    }

    public MyDetailsPage enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
        return this;
    }

    public MyDetailsPage clickSavePassword() {
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
