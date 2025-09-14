package com.smartgarage.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPanelPage extends BaseSmartGaragePage {

    @FindBy(xpath = "//a[@href='/users']")
    private WebElement allUsersTab;

    @FindBy(xpath = "//a[@href='/client-cars']")
    private WebElement clientCars;

    @FindBy(xpath = "//a[@href='/orders']")
    private WebElement orders;

    @FindBy(xpath = "//a[@href='/users/employee']")
    private WebElement createEmployee;

    @FindBy(xpath = "//a[@href='/users/mechanic']")
    private WebElement createMechanic;

    @FindBy(xpath = "//a[@href='/users/customer']")
    private WebElement createCustomer;

    public AdminPanelPage() {super("admin");}

    public void clickAllUsers() {
        waitForElementToBeClickable(allUsersTab);
        allUsersTab.click();
    }

    public void clickClientCars() {
        waitForElementToBeClickable(clientCars);
        clientCars.click();
    }

    public void clickOrders() {
        waitForElementToBeClickable(orders);
        orders.click();
    }

    public void clickCreateEmployee() {
        waitForElementToBeClickable(createEmployee);
        createEmployee.click();
    }

    public void clickCreateMechanic() {
        waitForElementToBeClickable(createMechanic);
        createMechanic.click();
    }

    public void clickCreateCustomer() {
        waitForElementToBeClickable(createCustomer);
        createCustomer.click();
    }
}
