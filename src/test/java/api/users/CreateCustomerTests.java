package api.users;

import com.github.javafaker.Faker;
import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCustomerTests {
    private final CustomerApi customerApi = new CustomerApi();
    private final Faker faker = new Faker();

    private Users originalCustomer;
    private Integer customerId;
    private String customerPassword;

    @BeforeEach
    void setupTestUser() {
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
    }

    @AfterEach
    void teardownTestUser() {
        if (customerId != null) {
            customerApi.deleteUser(customerId);
        }
    }

    @Test
    @DisplayName("Should Create a Customer Successfully")
    void customerSuccessfullyCreated() {
        assertNotNull(customerId);
        assertNotNull(customerPassword);
    }

    @Test
    @DisplayName("Should Update a Customer's Phone Number (self-auth)")
    void customerPhoneSuccessfullyUpdated() {
        Users updatedCustomer = new Users();
        updatedCustomer.setEmail(originalCustomer.getEmail());
        updatedCustomer.setPhoneNumber(faker.number().digits(10));
        updatedCustomer.setFirstName(originalCustomer.getFirstName());
        updatedCustomer.setLastName(originalCustomer.getLastName());

        Response updateResponse = customerApi.userSelfUpdate(
                customerId,
                originalCustomer.getUserName(),
                customerPassword,
                updatedCustomer
        );
        assertEquals(200, updateResponse.getStatusCode(), "Phone number was not updated.");
    }

    @Test
    @DisplayName("Should Delete a Customer Successfully")
    void customerSuccessfullyDeleted() {
        assertEquals(200, customerApi.deleteUser(customerId).getStatusCode(), "Delete call did not return 200.");

        Response getResponse = customerApi.getUserById(customerId);
        assertEquals(404, getResponse.getStatusCode(), "Customer still exists after deletion.");

        customerId = null;
    }
}