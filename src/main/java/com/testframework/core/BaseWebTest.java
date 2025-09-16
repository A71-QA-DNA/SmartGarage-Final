package com.testframework.core;

import com.github.javafaker.Faker;
import com.smartgarage.pages.*;
import com.smartgarage.pages.sections.NavigationSection;
import com.testframework.Driver;
import com.testframework.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BaseWebTest {
    protected Faker faker;
    protected LoginPage loginPage;
    protected AdminPanelPage adminPanelPage;
    protected ServicesPage servicesPage;
    protected ServiceOverviewPage serviceOverviewPage;
    protected CreateCustomerPage createCustomerPage;
    protected CreateEmployeePage createEmployeePage;
    protected CreateMechanicPage createMechanicPage;
    protected NavigationSection navigationSection;
    protected MyDetailsPage myDetailsPage;
    protected HomePage homePage;
    protected AllUsersPage allUsersPage;

    @BeforeEach
    public void before() {
        loginPage = new LoginPage();
        faker = new Faker();
        adminPanelPage = new AdminPanelPage();
        servicesPage = new ServicesPage();
        serviceOverviewPage = new ServiceOverviewPage();
        createCustomerPage = new CreateCustomerPage();
        createEmployeePage = new CreateEmployeePage();
        createMechanicPage = new CreateMechanicPage();
        navigationSection = new NavigationSection();
        myDetailsPage = new MyDetailsPage();
        homePage = new HomePage();
        allUsersPage = new AllUsersPage();
    }

    public static Driver driver() {
        return DriverManager.getDriver();
    }

    public static WebDriverWait driverWait() {
        return driver().getDriverWait();
    }

    @AfterEach
    public void afterEach() {
        driver().close();
    }
}