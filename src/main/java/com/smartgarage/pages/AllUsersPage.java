package com.smartgarage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;

public class AllUsersPage extends BaseSmartGaragePage {

    @FindBy(css = "form.filter-form")
    private WebElement filterForm;

    @FindBy(css = "form.filter-form input[name='username']")
    private WebElement usernameInput;

    @FindBy(css = "form.filter-form input[name='email']")
    private WebElement emailInput;

    @FindBy(css = "form.filter-form input[name='phoneNumber']")
    private WebElement phoneInput;

    @FindBy(css = "form.filter-form select[name='vehicleBrand']")
    private WebElement vehicleBrandSelect;

    @FindBy(css = "form.filter-form #visitDateFrom")
    private WebElement visitFromInput;

    @FindBy(css = "form.filter-form #visitDateTo")
    private WebElement visitToInput;

    @FindBy(css = "form.filter-form select[name='sortBy']")
    private WebElement sortBySelect;

    @FindBy(css = "form.filter-form select[name='sortDirection']")
    private WebElement sortDirSelect;

    @FindBy(css = "form.filter-form button.modern-button[type='submit']")
    private WebElement submitBtn;

    @FindBy(css = "div.user-list div.vehicle-item.white")
    private List<WebElement> resultRows;

    public AllUsersPage() {
        super("users");
    }

    public void typeUsername(String username) {
        clearAndType(usernameInput, username);
    }

    public void typeEmail(String email) {
        clearAndType(emailInput, email);
    }

    public void typePhone(String phone) {
        clearAndType(phoneInput, phone);
    }

    public void selectVehicleBrand(String brand) {
        Select brandSelect = new Select(vehicleBrandSelect);
        if (brand == null || brand.isEmpty()) {
            brandSelect.selectByVisibleText("All Brands");
        } else {
            brandSelect.selectByVisibleText(brand);
        }
    }

    public void typeVisitDateFrom(String from) {
        clearAndType(visitFromInput, from == null ? "" : from);
    }

    public void typeVisitDateTo(String to) {
        clearAndType(visitToInput, to == null ? "" : to);
    }

    public void selectSortBy(String valueOrEmpty) {
        Select sel = new Select(sortBySelect);
        if (valueOrEmpty == null || valueOrEmpty.isBlank()) {
            sel.selectByVisibleText("Sort By");
        } else {
            sel.selectByValue(valueOrEmpty);
        }
    }

    public void selectSortDirectionAsc(boolean asc) {
        new Select(sortDirSelect).selectByValue(asc ? "asc" : "desc");
    }

    public void clickSearch() {
        String beforeDom = driver().getPageSource();
        submitBtn.click();
        driverWait().until(d -> !Objects.equals(driver().getPageSource(), beforeDom));
    }

    public List<WebElement> getResultRows() {
        return driver().findElements(By.cssSelector("div.user-list div.vehicle-item.white"));
    }

    public String extractUsername(WebElement row) {
        WebElement element = row.findElement(By.xpath(".//div[contains(@class,'column')][1]//a"));
        waitForElementToBeVisible(element);
        return element.getText().trim();

    }

    public String extractEmail(WebElement row) {
        WebElement element = row.findElement(By.xpath(".//div[contains(@class,'column')][2]"));
        waitForElementToBeVisible(element);
        return element.getText().trim();
    }

    public String extractPhone(WebElement row) {
        WebElement element = row.findElement(By.xpath(".//div[contains(@class,'column')][3]"));
        waitForElementToBeVisible(element);
        return element.getText().trim();
    }

    private void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
