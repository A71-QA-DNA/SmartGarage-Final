package com.smartgarage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ClientCarsPage extends BaseSmartGaragePage {

    @FindBy(xpath = "//div[@class='custom-form-header']/h2[@class='box-header']")
    private WebElement clientCarsHeading;

    @FindBy(xpath = "//input[@id='vin']")
    private WebElement vinField;

    @FindBy(xpath = "//input[@id='licensePlate']")
    private WebElement licensePlateField;

    @FindBy(xpath = "//input[@id='owner']")
    private WebElement ownerField;

    @FindBy(xpath = "//select[@id='brand']")
    private WebElement brandField;

    @FindBy(xpath = "//select[@id='brand']/option[@value='Audi']")
    private WebElement brandOptionAudi;

    @FindBy(xpath = "//input[@id='make']")
    private WebElement makeField;

    @FindBy(xpath = "//input[@id='engineType']")
    private WebElement engineTypeField;

    @FindBy(xpath = "//input[@id='yearOfCreation']")
    private WebElement yearOfCreationField;

    @FindBy(xpath = "//div[@class='custom-form-row']/button")
    private WebElement addClientButton;

    @FindBy(xpath = "//div[@class='error-message']")
    private WebElement errorMessage;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement searchField;

    @FindBy(xpath = "//select[@id='sort-by']")
    private WebElement sortByButton;

    @FindBy(xpath = "//select[@id='sort-by']/option[text()='Owner First Name']")
    private WebElement sortByOwnerFirstNameButton;

    @FindBy(xpath = "//select[@id='sort-by']/option[text()='Owner Username']")
    private WebElement sortByOwnerUsernameButton;

    @FindBy(xpath = "//select[@id='order']")
    private WebElement sortingOrderButton;

    @FindBy(xpath = "//select[@id='order']/option[@value='asc']")
    private WebElement ascendingSearchButton;

    @FindBy(xpath = "//select[@id='order']/option[@value='desc']")
    private WebElement descendingSearchButton;

    @FindBy(xpath = "//button[@type='submit']/i[@class='fa fa-search']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[text()='Edit']")
    private WebElement editButton;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//a[text()='Add Services']")
    private WebElement addServicesButton;

    public ClientCarsPage() {
        super("client-cars");
    }

    public void fillVehicleForm(String vin, String licensePlate, String owner, String make, String engineType, String year) {
        waitForElementToBeVisible(vinField);
        vinField.clear();
        vinField.sendKeys(vin);

        licensePlateField.clear();
        licensePlateField.sendKeys(licensePlate);

        ownerField.clear();
        ownerField.sendKeys(owner);

        waitForElementToBeClickable(brandField);
        brandField.click();
        waitForElementToBeClickable(brandOptionAudi);
        brandOptionAudi.click();

        makeField.clear();
        makeField.sendKeys(make);

        engineTypeField.clear();
        engineTypeField.sendKeys(engineType);

        yearOfCreationField.clear();
        yearOfCreationField.sendKeys(year);
    }

    public void clickAddClientButton() {
        waitForElementToBeClickable(addClientButton);
        addClientButton.click();
    }

    public void searchForVehicle(String owner) {
        waitForElementToBeVisible(searchField);
        searchField.clear();
        searchField.sendKeys(owner);
        waitForElementToBeClickable(searchButton);
        searchButton.click();
    }

    public void selectSortBy(String sortBy) {
        waitForElementToBeClickable(sortByButton);
        sortByButton.click();

        if (sortBy.equals("Owner First Name")) {
            waitForElementToBeClickable(sortByOwnerFirstNameButton);
            sortByOwnerFirstNameButton.click();
        } else if (sortBy.equals("Owner Username")) {
            waitForElementToBeClickable(sortByOwnerUsernameButton);
            sortByOwnerUsernameButton.click();
        }
    }

    public void selectSortOrder(String order) {
        waitForElementToBeClickable(sortingOrderButton);
        sortingOrderButton.click();

        if (order.equals("asc")) {
            waitForElementToBeClickable(ascendingSearchButton);
            ascendingSearchButton.click();
        } else if (order.equals("desc")) {
            waitForElementToBeClickable(descendingSearchButton);
            descendingSearchButton.click();
        }
    }

    public void clickEditButton() {
        waitForElementToBeClickable(editButton);
        editButton.click();
    }

    public void clickSaveButton() {
        waitForElementToBeClickable(saveButton);
        saveButton.click();
    }

    public void clickDeleteButton() {
        waitForElementToBeClickable(deleteButton);
        deleteButton.click();
    }

    public void clickAddServicesButton() {
        waitForElementToBeClickable(addServicesButton);
        addServicesButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            waitForElementToBeVisible(errorMessage);
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            return errorMessage.getText();
        }
        return "";
    }

    public String getVinFieldValue() {
        return vinField.getAttribute("value");
    }

    public String getLicensePlateFieldValue() {
        return licensePlateField.getAttribute("value");
    }

    public String getOwnerFieldValue() {
        return ownerField.getAttribute("value");
    }

    public String getMakeFieldValue() {
        return makeField.getAttribute("value");
    }

    public String getEngineTypeFieldValue() {
        return engineTypeField.getAttribute("value");
    }

    public String getYearOfCreationFieldValue() {
        return yearOfCreationField.getAttribute("value");
    }

    public boolean isFormCleared() {
        return getVinFieldValue().isEmpty() &&
                getLicensePlateFieldValue().isEmpty() &&
                getOwnerFieldValue().isEmpty() &&
                getMakeFieldValue().isEmpty() &&
                getEngineTypeFieldValue().isEmpty() &&
                getYearOfCreationFieldValue().isEmpty();
    }

    public boolean areVehiclesDisplayed() {
        try {
            List<WebElement> vehicleRows = driver().findElements(By.xpath("//table//tr[position()>1]"));
            return !vehicleRows.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public int getVehicleCount() {
        try {
            List<WebElement> vehicleRows = driver().findElements(By.xpath("//table//tr[position()>1]"));
            return vehicleRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isVehicleDisplayed() {
        try {
            WebElement firstVehicle = driver().findElement(By.xpath("//div[@class='vehicle-item white custom-car-list-row']/div[starts-with(@id,'vin')]"));
            return firstVehicle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstVehicleVin() {
        try {
            WebElement firstVehicle = driver().findElement(By.xpath("//div[@class='vehicle-item white custom-car-list-row']/div[starts-with(@id,'vin')]"));
            return firstVehicle.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
