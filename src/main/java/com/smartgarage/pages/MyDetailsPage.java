package com.smartgarage.pages;

import com.testframework.core.BaseWebPage;
import org.openqa.selenium.WebDriver;

public class MyDetailsPage extends BaseWebPage {

    protected MyDetailsPage(WebDriver webDriver) {
        super(webDriver, "users/6/details");
    }
}
