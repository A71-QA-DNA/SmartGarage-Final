package com.smartgarage.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ServicesPage extends BaseSmartGaragePage {

    @FindBy(css = "ul.services-list a[href='/services/engine-diagnostics']")
    private WebElement engineDiagnosticsLink;

    @FindBy(css = "ul.services-list a[href='/services/lube-oil-filters']")
    private WebElement lubeOilFiltersLink;

    @FindBy(css = "ul.services-list a[href='/services/belts-hoses']")
    private WebElement beltsHosesLink;

    @FindBy(css = "ul.services-list a[href='/services/air-conditioning']")
    private WebElement airConditioningLink;

    @FindBy(css = "ul.services-list a[href='/services/brake-repair']")
    private WebElement brakeRepairLink;

    @FindBy(css = "ul.services-list a[href='/services/tire-wheel']")
    private WebElement tireWheelLink;

    public ServicesPage() {
        super("services");
    }

    public void clickEngineDiagnosticsLink() {
        waitForElementToBeVisible(engineDiagnosticsLink);
        engineDiagnosticsLink.click();
    }

    public void clickLubeOilFiltersLink() {
        waitForElementToBeVisible(lubeOilFiltersLink);
        lubeOilFiltersLink.click();
    }

    public void clickBeltsHosesLink() {
        waitForElementToBeVisible(beltsHosesLink);
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
