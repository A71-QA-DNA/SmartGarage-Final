package com.smartgarage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ServiceOverviewPage extends BaseSmartGaragePage {

    @FindBy(id = "servicesTable")
    private WebElement servicesTable;

    @FindBy(id = "addServiceButton")
    private WebElement addServiceButton;

    @FindBy(css = "tr input[name='serviceName']")
    private WebElement newServiceNameField;

    @FindBy(css = "tr input[name='servicePrice']")
    private WebElement newServicePriceField;

    public ServiceOverviewPage() {
        super("");
    }

    public void clickAddService() {
        waitForElementToBeClickable(addServiceButton);
        addServiceButton.click();
    }

    public void enterNewService(String name, String price) {
        waitForElementToBeVisible(newServiceNameField);
        newServiceNameField.clear();
        newServiceNameField.sendKeys(name);

        waitForElementToBeVisible(newServicePriceField);
        newServicePriceField.clear();
        newServicePriceField.sendKeys(price);
    }

    public WebElement getServiceRowById(int serviceId) {
        return servicesTable.findElement(By.cssSelector("tr[data-service-id='" + serviceId + "']"));
    }

    public String getServiceName(int serviceId) {
        WebElement element = getServiceRowById(serviceId).findElement(By.id("service-name-" + serviceId));
        waitForElementToBeVisible(element);
        return element.getText();
    }

    public String getServicePrice(int serviceId) {
        By span = By.cssSelector("#service-price-" + serviceId + " span");
        WebElement priceSpan = driverWait().until(ExpectedConditions.visibilityOfElementLocated(span));
        return priceSpan.getText();
    }

    public void clickEdit(int serviceId) {
        WebElement editBtn = getServiceRowById(serviceId).findElement(By.id("edit-" + serviceId));
        waitForElementToBeClickable(editBtn);
        editBtn.click();
    }

    public void clickSave(int serviceId) {
        WebElement saveBtn = getServiceRowById(serviceId).findElement(By.id("save-" + serviceId));
        waitForElementToBeClickable(saveBtn);
        saveBtn.click();
    }

    public void enterPriceForService(int serviceId, String newPrice) {
        WebElement priceInput = driver().findElement(
                By.cssSelector("tr[data-service-id='" + serviceId + "'] input[type='number']")
        );
        priceInput.clear();
        priceInput.sendKeys(newPrice);
    }

    public int getServiceCount() {
        return servicesTable.findElements(By.cssSelector("tr[data-service-id]")).size();
    }

    public void saveNewService() {
        WebElement saveButton = driver().findElement(By.cssSelector("#saveButtonContainer button"));
        waitForElementToBeClickable(saveButton);
        saveButton.click();
    }
}
