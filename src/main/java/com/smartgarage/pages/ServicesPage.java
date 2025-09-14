package com.smartgarage.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ServicesPage extends BaseSmartGaragePage {

    @FindBy(css = "ul.services-list a[title='Engine Diagnostics']")
    private WebElement engineDiagnosticsLink;

    @FindBy(css = "ul.services-list a[title='Lube, Oil and Filters']")
    private WebElement lubeOilFiltersLink;

    @FindBy(css = "ul.services-list a[title='Belts and Hoses']")
    private WebElement beltsHosesLink;

    @FindBy(css = "ul.services-list a[title='Air Conditioning']")
    private WebElement airConditioningLink;

    @FindBy(css = "ul.services-list a[title='Brake Repair']")
    private WebElement brakeRepairLink;

    @FindBy(css = "ul.services-list a[title='Tire and Wheel Services']")
    private WebElement tireWheelLink;

    public ServicesPage() {
        super("services");
    }

    public void clickEngineDiagnosticsLink() {
        waitForElementToBeClickable(engineDiagnosticsLink);
        engineDiagnosticsLink.click();
    }

    public void clickLubeOilFiltersLink() {
        waitForElementToBeClickable(lubeOilFiltersLink);
        lubeOilFiltersLink.click();
    }

    public void clickBeltsHosesLink() {
        waitForElementToBeClickable(beltsHosesLink);
        beltsHosesLink.click();
    }

    public void clickAirConditioningLink() {
        waitForElementToBeClickable(airConditioningLink);
        airConditioningLink.click();
    }

    public void clickBrakeRepairLink() {
        waitForElementToBeClickable(brakeRepairLink);
        brakeRepairLink.click();
    }

}
