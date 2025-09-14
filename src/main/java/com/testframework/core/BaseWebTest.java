package com.testframework.core;

import com.smartgarage.pages.*;
import com.testframework.Driver;
import com.testframework.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BaseWebTest {

    protected LoginPage loginPage;
    protected AdminPanelPage adminPanelPage;
    protected ServicesPage servicesPage;
    protected ServiceOverviewPage serviceOverviewPage;

    @BeforeEach
    public void before() {
        loginPage = new LoginPage();
        adminPanelPage = new AdminPanelPage();
        servicesPage = new ServicesPage();
        serviceOverviewPage = new ServiceOverviewPage();
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