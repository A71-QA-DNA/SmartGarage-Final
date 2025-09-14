package com.smartgarage.pages;

import com.testframework.core.BaseWebPage;
import org.openqa.selenium.WebDriver;

public class AdminPanelPage extends BaseWebPage {

    protected AdminPanelPage(WebDriver webDriver) {
        super(webDriver, "admin-panel");
    }
}
