package com.smartgarage.pages;

import com.testframework.core.BaseWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseWebPage {

    public HomePage (WebDriver webDriver) {
        super(webDriver, "");
    }

    @Override
    protected String getPageUrl() {
        return super.getPageUrl();
    }
}
