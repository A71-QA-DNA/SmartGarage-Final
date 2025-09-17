package web.entities;

import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import com.testframework.core.BaseWebTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@Epic("Private")
@Epic("Entities")
@Feature("Customer Profile Edit")
@Tag("system")
public class CustomerEditProfileTests extends BaseWebTest {

    private CustomerApi customerApi;
    private Users originalCustomer;
    private Integer customerId;
    private String customerPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String newPassword;

    @BeforeEach
    void setupTestUser() {
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
    }

    @AfterEach
    void tearDown() {
        customerApi.deleteUser(customerId);
    }

    @Test
    @DisplayName("Customer should be able to edit his first name")
    void editFirstName() {
        firstName = faker.name().firstName();
        navigationSection.clickMyDetailsButton();
        myDetailsPage.clickEditInfo();
        myDetailsPage.editFirstName(firstName);
        myDetailsPage.clickSaveInfo();
        driver().navigate().refresh();
        assertTrue(myDetailsPage.getFullName().contains(firstName));
    }

    @Test
    @DisplayName("Customer should be able to edit last name")
    void editLastName() {
        lastName = faker.name().lastName();
        navigationSection.clickMyDetailsButton();
        myDetailsPage.clickEditInfo();
        myDetailsPage.editLastName(lastName);
        myDetailsPage.clickSaveInfo();
        driver().navigate().refresh();
        assertTrue(myDetailsPage.getFullName().contains(lastName));
    }

    @Test
    @DisplayName("Customer should be able to edit email")
    void editLastNameWithEmail() {
        email = faker.internet().emailAddress();
        navigationSection.clickMyDetailsButton();
        myDetailsPage.clickEditInfo();
        myDetailsPage.editEmail(email);
        myDetailsPage.clickSaveInfo();
        driver().navigate().refresh();
        assertTrue(myDetailsPage.getEmail().contains(email));
    }

    @Test
    @DisplayName("Customer should be able to edit phone number")
    void  editPhoneNumber() {
        phone = faker.number().digits(10);
        navigationSection.clickMyDetailsButton();
        myDetailsPage.clickEditInfo();
        myDetailsPage.editPhone(phone);
        myDetailsPage.clickSaveInfo();
        driver().navigate().refresh();
        assertTrue(myDetailsPage.getPhone().contains(phone));
    }

    @Test
    @DisplayName("Customer should be able to edit password")
    void editPassword() {
        newPassword = faker.internet().password(8, 10, true, true, true);
        navigationSection.clickMyDetailsButton();
        myDetailsPage.clickChangePassword();
        myDetailsPage.enterOldPassword(customerPassword);
        myDetailsPage.enterNewPassword(newPassword);
        myDetailsPage.enterConfirmPassword(newPassword);
        myDetailsPage.clickSavePassword();
        driver().navigate().refresh();
        loginPage.logout();
        loginPage.navigate();
        loginPage.loginAs(originalCustomer.getUserName(), newPassword);
        assertTrue(navigationSection.myDetailsButtonIsVisible(), "My details section is not visible.");
    }
}
