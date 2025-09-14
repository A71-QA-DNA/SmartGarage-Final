package com.smartgarage.pages;

import com.testframework.core.BaseWebPage;
import org.openqa.selenium.WebDriver;

public class VehiclesPage extends BaseWebPage {
    protected VehiclesPage(WebDriver webDriver) {
        super(webDriver, "vehicles");
    }
}
