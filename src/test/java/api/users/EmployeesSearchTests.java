package api.users;

import com.github.javafaker.Faker;
import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeesSearchTests {
    private final CustomerApi customerApi = new CustomerApi();
    private final Faker faker = new Faker();

    private Users originalCustomer;
    private Integer customerId;
    private String customerUserName;

    @BeforeEach
    void setupTestUser() {
        originalCustomer = new Users();
        originalCustomer.setUserName(faker.name().username());
        originalCustomer.setEmail(faker.internet().emailAddress());
        originalCustomer.setPhoneNumber(faker.number().digits(10));
        originalCustomer.setFirstName(faker.name().firstName());
        originalCustomer.setLastName(faker.name().lastName());
        customerApi.createCustomerAndExtract(originalCustomer);

        Users fetched = customerApi.findUserByUsername(originalCustomer.getUserName());
        customerId = fetched.getUserId();
        customerUserName = fetched.getUserName();
        assertNotNull(customerId, "User ID was not returned by search.");
    }

    @AfterEach
    void teardownTestUser() {

        if (customerId != null) {
            customerApi.deleteUser(customerId);
        }
    }

    @Test
    @DisplayName("Admin should Find Customer by Username")
    void userFoundSuccessfully_when_adminSearchesByUsername() {
        assertNotNull(customerUserName, "Customer not found by Username after creation.");
        assertEquals(originalCustomer.getUserName(), customerUserName, "Customer not found");
    }

    @Test
    @DisplayName("Admin should Find Customer by Email")
    void userFoundSuccessfully_when_adminSearchesByEmail() {
        Users fetchEmail = customerApi.findUserByEmail(originalCustomer.getEmail());
        assertNotNull(fetchEmail, "Customer not found by Email after creation.");
        assertEquals(originalCustomer.getUserName(), fetchEmail.getUserName(), "Customer email does not match");

    }

    @Test
    @DisplayName("Admin shoudl find customer by Phone")
    void userFoundSuccessfully_when_adminSearchesByPhoneNumber() {
        Users fetchPhone = customerApi.findUserByPhone(originalCustomer.getPhoneNumber());
        assertNotNull(fetchPhone, "Customer not found by Phone number after creation.");
        assertEquals(originalCustomer.getPhoneNumber(), fetchPhone.getPhoneNumber(), "Customer email does not match");
    }

}
