package com.smartgarage.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ServiceOverviewPage extends BaseSmartGaragePage {

    @FindBy(id = "addServiceButton")
    private WebElement addServiceButton;

    @FindBy(css = "tr input[name='serviceName']")
    private WebElement newServiceNameField;

    @FindBy(css = "tr input[name='servicePrice']")
    private WebElement newServicePriceField;

    private final By saveNewServiceLink = By.cssSelector("#saveButtonContainer a");

    private final By TABLE_ROWS = By.cssSelector("#servicesTable tr[data-service-id]");

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

    public WebElement getServiceRowById(String serviceId) {
        By rowBy = By.cssSelector("tr[data-service-id='" + serviceId + "']");
        return driver().findElement(rowBy);
    }

    public String getServiceRowByName(String name) {
        By newName = By.id("service-name-" + name);
        return driver().getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(newName))
                .getText().trim();
    }

    public String findNewServiceId(String name) {
        By rowBy = By.xpath("//tr[@data-service-id][td[normalize-space()='" + name + "']]");
        WebElement row = driverWait().until(ExpectedConditions.presenceOfElementLocated(rowBy));
        return row.getAttribute("data-service-id");
    }

    public void clickSaveNewService() {
        WebElement save = driver().findElement(saveNewServiceLink);
        waitForElementToBeClickable(save);
        save.click();
    }

    public void deleteServiceById(String serviceId) {
        By deleteBy = By.id("delete-" + serviceId);
        WebElement deleteBtn = driver().findElement(deleteBy);
        waitForElementToBeClickable(deleteBtn);
        deleteBtn.click();
        driver().switchTo().alert().accept();
    }

    public String getServicePrice(String serviceId) {
        By span = By.cssSelector("#service-price-" + serviceId + " span");
        WebElement price = driverWait().until(ExpectedConditions.visibilityOfElementLocated(span));
        return price.getText();
    }

    public void clickEdit(String serviceId) {
        WebElement editBtn = getServiceRowById(serviceId).findElement(By.id("edit-" + serviceId));
        waitForElementToBeClickable(editBtn);
        editBtn.click();
    }

    public void clickSave(String serviceId) {
        WebElement saveBtn = getServiceRowById(serviceId).findElement(By.id("save-" + serviceId));
        waitForElementToBeClickable(saveBtn);
        saveBtn.click();
    }

    public void enterPriceForService(String serviceId, String newPrice) {
        WebElement priceInput = driver().findElement(
                By.cssSelector("tr[data-service-id='" + serviceId + "'] input[type='number']")
        );
        priceInput.clear();
        priceInput.sendKeys(newPrice);
    }

    public int getServiceCount() {
        return driver().findElements(TABLE_ROWS).size();
    }
}
