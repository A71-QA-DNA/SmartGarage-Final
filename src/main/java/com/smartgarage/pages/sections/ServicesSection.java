package com.smartgarage.pages.sections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ServicesSection extends BaseSection {

    public ServicesSection() {
        super();

    }

    @FindBy(xpath = "//a[@href='/services/engine-diagnostics']")
    private WebElement engineDiagnosticsButton;

    @FindBy(xpath = "//a[@href='/services/lube-oil-filters']")
    private WebElement lubeOilFiltersButton;

    @FindBy(xpath = "//a[@href='/services/air-conditioning']")
    private WebElement airConditioningButton;

    @FindBy(xpath = "//a[@href='/services/brake-repair']")
    private WebElement brakeRepairButton;

    @FindBy(xpath = "//a[@href='/services/tire-wheel']")
    private WebElement tireWheelButton;

    @FindBy(xpath = "//a[@href='/services/belts-hoses']")
    private WebElement beltsHosesButton;
}
