package com.smartgarage.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseSmartGaragePage {

    @FindBy(xpath = "//a[@title='CarService']") private WebElement smartGarageHeader;

    public HomePage() {
        super("");
    }

    public String getHeaderText(){
        waitForElementToBeVisible(smartGarageHeader);
        return smartGarageHeader.getText();
    }
}
