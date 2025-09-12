package com.smartgarage.pages.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseSection {

    protected final WebDriver webDriver;

    protected BaseSection(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected WebElement createByXpath(String xpath) {
        return createBy(By.xpath(xpath));
    }

    private WebElement createBy(By by) {
        return webDriver.findElement(by);
    }
}
