package com.smartgarage.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateEmployeePage extends BaseSmartGaragePage{

    @FindBy(id = "reg-username") private WebElement usernameField;
    @FindBy(id = "reg-email") private WebElement emailField;
    @FindBy(id = "reg-first-name") private WebElement firstNameField;
    @FindBy(id = "reg-last-name") private WebElement lastNameField;
    @FindBy(id = "reg-phone") private WebElement phoneField;
    @FindBy(xpath = "//button[@type='submit']") private WebElement registerButton;
    @FindBy(xpath = "//div[@class='login-register-box']//p[1]/span") private WebElement userNameSpan;

    public CreateEmployeePage(){
        super("users/employee");
    }

    public void fillForm(String username, String email, String firstName, String lastName, String phone){
        usernameField.clear();
        usernameField.sendKeys(username);
        emailField.clear();
        emailField.sendKeys(email);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void clickRegisterButton(){
        registerButton.click();
    }

    public String getCreatedUsername() {
        waitForElementToBeVisible(userNameSpan);
        return userNameSpan.getText().trim();
    }
}

