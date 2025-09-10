package com.testframework.core;

import com.testframework.Driver;
import com.testframework.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BaseWebTest {

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