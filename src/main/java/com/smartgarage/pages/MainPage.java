package com.smartgarage.pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getUrl() {
        return "http://localhost:8081/";
    }
}
