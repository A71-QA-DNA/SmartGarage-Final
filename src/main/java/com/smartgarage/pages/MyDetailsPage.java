package com.smartgarage.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyDetailsPage extends BaseSmartGaragePage {

    @FindBy(xpath = "//label[@for='avatarFile']") private WebElement choosePhotoButton;

    @FindBy(xpath = "//button[@id='edit-info-button']") private WebElement editInfoButton;

    @FindBy(xpath = "//input[@id='first-name-input']") private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='last-name-input']") private WebElement lastNameField;

    @FindBy(xpath = "//span[@id='username-text']") private WebElement usernameField;

    @FindBy(xpath = "//input[@id='email-input']") private WebElement emailField;

    @FindBy(xpath = "//input[@id='phone-input']") private WebElement phoneField;

    @FindBy(xpath = "//span[@id='role-text']") private WebElement roleField;

    @FindBy(xpath = "//button[@id='save-info-button']") private WebElement saveInfoButton;

    @FindBy(id = "full-name-text") private WebElement fullNameText;

    @FindBy(id = "email-text") private  WebElement emailText;

    @FindBy(id = "phone-text") private  WebElement phoneText;

    @FindBy(xpath = "//a[@class='custom-button']") private WebElement changePasswordButton;

    @FindBy(xpath = "//input[@id='old-password']") private WebElement oldPasswordField;

    @FindBy(xpath = "//input[@id='new-password']") private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirm-password']") private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']") private WebElement savePasswordButton;

    public MyDetailsPage() {
        super("");
    }

    public void clickChoosePhoto() {
        waitForElementToBeClickable(choosePhotoButton);
        choosePhotoButton.click();
    }

    public void clickEditInfo() {
        waitForElementToBeClickable(editInfoButton);
        editInfoButton.click();
    }

    public void editFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void editLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void editEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void editPhone(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void clickSaveInfo() {
        saveInfoButton.click();
    }

    public void clickChangePassword() {
        waitForElementToBeClickable(changePasswordButton);
        changePasswordButton.click();
    }

    public void enterOldPassword(String oldPassword) {
        oldPasswordField.clear();
        oldPasswordField.sendKeys(oldPassword);
    }

    public void enterNewPassword(String newPassword) {
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickSavePassword() {
        savePasswordButton.click();
    }

    public String getFullName(){
        waitForElementToBeVisible(fullNameText);
        return fullNameText.getText();
    }

    public String getEmail(){
        waitForElementToBeVisible(emailText);
        return emailText.getText();
    }

    public String getPhone(){
        waitForElementToBeVisible(phoneText);
        return phoneText.getText();
    }
}
