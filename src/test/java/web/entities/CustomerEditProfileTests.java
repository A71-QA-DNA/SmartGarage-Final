package web.entities;

import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import com.testframework.core.BaseWebTest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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
    private static final String SMART_HEADER = "SMART GARAGE TI";

    @BeforeEach
    void setupTestUser() {
        customerApi = new CustomerApi();
        originalCustomer = new Users();
        originalCustomer.setUserName(faker.name().username());
        originalCustomer.setEmail(faker.internet().emailAddress());
        originalCustomer.setPhoneNumber(faker.number().digits(10));
        originalCustomer.setFirstName(faker.name().firstName());
        originalCustomer.setLastName(faker.name().lastName());
        System.out.println(originalCustomer);

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
        assertTrue(myDetailsPage.getPhone().contains(phone));
    }

    @Test
    @DisplayName("Customer should be able to edit password")
    void editPassword() {
        newPassword = faker.internet().password(8, 10, true, true, true);
        navigationSection.clickMyDetailsButton();
        myDetailsPage.changePassword(customerPassword, newPassword);
        loginPage.logout();
        loginPage.navigate();
        loginPage.loginAs(originalCustomer.getUserName(), newPassword);
        assertTrue(homePage.getHeaderText().contains(SMART_HEADER));
    }
}
