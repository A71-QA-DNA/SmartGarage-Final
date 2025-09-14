package com.smartgarage.pages;

import com.testframework.core.BaseWebPage;
import org.openqa.selenium.WebDriver;

public class MyOrdersPage extends BaseWebPage {
    protected MyOrdersPage(WebDriver webDriver) {
        super(webDriver, "users/6/orders");
    }
}
