package com.smartgarage.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BaseSmartGaragePage {

    @FindBy(xpath = "//input[@id='old-password']")
    private WebElement oldPasswordField;

    @FindBy(xpath = "//input[@id='new-password']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirm-password']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement savePasswordButton;

    protected ChangePasswordPage() {
        super("users/6/password-change");
    }

    public ChangePasswordPage enterOldPassword(String oldPassword) {
        waitForElementToBeVisible(oldPasswordField);
        oldPasswordField.clear();
        oldPasswordField.sendKeys(oldPassword);
        return this;
    }

    public ChangePasswordPage enterNewPassword(String newPassword) {
        waitForElementToBeVisible(newPasswordField);
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
        return this;
    }

    public ChangePasswordPage enterConfirmPassword(String confirmPassword) {
        waitForElementToBeVisible(confirmPasswordField);
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
        return this;
    }

    public MyDetailsPage clickSavePassword() {
        waitForElementToBeClickable(savePasswordButton);
        savePasswordButton.click();
        return new MyDetailsPage();
    }

    public MyDetailsPage changePassword(String oldPassword, String newPassword) {
        return enterOldPassword(oldPassword)
                .enterNewPassword(newPassword)
                .enterConfirmPassword(newPassword)
                .clickSavePassword();
    }
}
