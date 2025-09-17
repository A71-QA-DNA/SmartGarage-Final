package web.publictests;

import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import com.testframework.PropertiesManager;
import com.testframework.core.BaseWebTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Public")
@Feature("User should be able to log in")
@Tag("system")
public class LoginTests extends BaseWebTest {

    private static final Properties CFG = PropertiesManager.getConfigProperties();
    private static final String USERNAME = CFG.getProperty("adminUsername");
    private static final String PASSWORD = CFG.getProperty("adminPassword");
    private CustomerApi customerApi;
    private Users originalCustomer;
    private Integer customerId;
    private String customerPassword;

    @Test
    @DisplayName("Admin should be able to log in the system")
    void AdminLogIn() {
        loginPage.navigate();
        loginPage.loginAs(USERNAME, PASSWORD);
        assertTrue(navigationSection.myDetailsButtonIsVisible(), "My details section is not visible.");
    }

    @Test
    @DisplayName("Customer should be able to log in the system")
    void CustomerLogin() {
        customerApi = new CustomerApi();
        originalCustomer = new Users();
        originalCustomer.setUserName(faker.name().username());
        originalCustomer.setEmail(faker.internet().emailAddress());
        originalCustomer.setPhoneNumber(faker.number().digits(10));
        originalCustomer.setFirstName(faker.name().firstName());
        originalCustomer.setLastName(faker.name().lastName());

        Users created = customerApi.createCustomerAndExtract(originalCustomer);
        customerPassword = created.getPassword();
        assertNotNull(customerPassword, "No password returned.");

        Users fetched = customerApi.findUserByUsername(originalCustomer.getUserName());
        assertNotNull(fetched, "User not found after creation.");
        customerId = fetched.getUserId();
        assertNotNull(customerId, "User ID was not returned by search.");
        loginPage.navigate();
        loginPage.loginAs(originalCustomer.getUserName(), customerPassword);
        assertTrue(navigationSection.myDetailsButtonIsVisible(), "My details section is not visible");
        customerApi.deleteUser(customerId);
    }
}
