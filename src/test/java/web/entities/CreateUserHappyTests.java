package web.entities;

import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import com.testframework.core.BaseWebTest;
import helper.FakerHelper;
import helper.UserData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Entities")
@Feature("User Registration by Admin")
@Tag("system")
public class CreateUserHappyTests extends BaseWebTest {

    private CustomerApi customerApi;
    private UserData userData;
    private Integer userId;


    @BeforeEach
    public void setup() {
        loginPage.navigate();
        loginPage.loginAs("felix_jackson", "password123%D");
        userData = FakerHelper.createRandomUser();
    }

    @AfterEach
    public void tearDown() {
        customerApi = new CustomerApi();
        Users fetched = customerApi.findUserByUsername(userData.getUserName());
        assertNotNull(fetched, "User not found after creation.");
        userId = fetched.getUserId();
        assertNotNull(userId, "User ID was not returned by search.");
        customerApi.deleteUser(userId);
    }

    @Test
    @DisplayName("Admin should Create a Customer Successfully")
    void customerSuccessfullyCreated() {
        navigationSection.clickAdminPanelButton();
        adminPanelPage.clickCreateCustomer();
        createCustomerPage.fillForm(
                userData.getUserName(),
                userData.getEmail(),
                userData.getFirstName(),
                userData.getLastName(),
                userData.getPhone()
                );
        createCustomerPage.clickRegisterButton();
        assertEquals(userData.getUserName(), createCustomerPage.getCreatedUsername(), "Customer was not created.");
    }

    @Test
    @DisplayName("Admin should create an Employee successfully")
    void employeeSuccessfullyCreated() {
        navigationSection.clickAdminPanelButton();
        adminPanelPage.clickCreateEmployee();
        createEmployeePage.fillForm(userData.getUserName(),
                userData.getEmail(),
                userData.getFirstName(),
                userData.getLastName(),
                userData.getPhone()
        );
        createEmployeePage.clickRegisterButton();
        assertEquals(userData.getUserName(), createEmployeePage.getCreatedUsername(), "Employee was not created.");
    }

    @Test
    @DisplayName("Admin should create a Mechanic successfully")
    void  mechanicSuccessfullyCreated() {
        navigationSection.clickAdminPanelButton();
        adminPanelPage.clickCreateMechanic();
        createMechanicPage.fillForm(userData.getUserName(),
                userData.getEmail(),
                userData.getFirstName(),
                userData.getLastName(),
                userData.getPhone()
        );
        createMechanicPage.clickRegisterButton();
        assertEquals(userData.getUserName(), createMechanicPage.getCreatedUsername(), "Employee was not created.");
    }
}
