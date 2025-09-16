package web.entities;

import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import com.testframework.core.BaseWebTest;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Entities")
@Tag("system")
public class CreateUserHappyTests extends BaseWebTest {

    private CustomerApi  customerApi;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Integer userId;

    @BeforeEach
    public void setup() {
        loginPage.navigate();
        loginPage.loginAs("felix_jackson", "password123%D");
        userName = faker.name().username();
        email = faker.internet().emailAddress();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        phone = faker.number().digits(10);
    }

    @AfterEach
    public void tearDown() {
        customerApi = new CustomerApi();
        Users fetched = customerApi.findUserByUsername(userName);
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
        createCustomerPage.fillForm(userName, email, firstName, lastName, phone);
        createCustomerPage.clickRegisterButton();
        assertEquals(userName, createCustomerPage.getCreatedUsername(), "Customer was not created.");
    }

    @Test
    @DisplayName("Admin should create an Employee successfully")
    void employeeSuccessfullyCreated() {
        navigationSection.clickAdminPanelButton();
        adminPanelPage.clickCreateEmployee();
        createEmployeePage.fillForm(userName, email, firstName, lastName, phone);
        createEmployeePage.clickRegisterButton();
        assertEquals(userName, createEmployeePage.getCreatedUsername(), "Employee was not created.");
    }

    @Test
    @DisplayName("Admin should create a Mechanic successfully")
    void  mechanicSuccessfullyCreated() {
        navigationSection.clickAdminPanelButton();
        adminPanelPage.clickCreateMechanic();
        createMechanicPage.fillForm(userName, email, firstName, lastName, phone);
        createMechanicPage.clickRegisterButton();
        assertEquals(userName, createMechanicPage.getCreatedUsername(), "Employee was not created.");
        System.out.println(userName);
    }
}
