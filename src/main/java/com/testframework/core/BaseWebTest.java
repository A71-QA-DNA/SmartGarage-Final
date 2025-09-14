package com.testframework.core;

import com.smartgarage.pages.AdminPanelPage;
import com.smartgarage.pages.LoginPage;
import com.testframework.Driver;
import com.testframework.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BaseWebTest {

    protected LoginPage loginPage;
    protected AdminPanelPage adminPanelPage;

    @BeforeEach
    public void before() {
        loginPage = new LoginPage();
        adminPanelPage = new AdminPanelPage();
    }

    public static Driver driver() {
        return DriverManager.getDriver();
    }

    public static WebDriverWait driverWait() {
        return driver().getDriverWait();
    }

    @AfterAll
    public static void afterAll() {
        driver().close();
    }
}