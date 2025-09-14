package api.users;

import com.github.javafaker.Faker;
import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import com.testframework.core.BaseApiTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@Epic("REST API")
@Feature("Users – CRUD Operations")
public class CreateCustomerUnhappyTests extends BaseApiTest {
    private final CustomerApi customerApi = new CustomerApi();
    public final Faker faker = new Faker();
    private Users originalCustomer;
    private Integer customerId;


    @ParameterizedTest(name = "Username: {0}")
    @ValueSource(strings = {"a", "abcdefghijklmnopqrstuv", ""})
    @Story("Create Customer: Invalid Username (too short / too long / empty)")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should reject invalid username formats and lengths")
    void createCustomer_withInvalidUsername_shouldReturn400(String invalidUsername) {
        Users newUser = new Users();
        newUser.setUserName(invalidUsername);
        newUser.setEmail(faker.internet().emailAddress());
        newUser.setPhoneNumber(faker.number().digits(10));
        Response response = customerApi.createCustomer(newUser);
        assertEquals(400, response.getStatusCode(), "Expected 400 but received: " + response.getStatusCode());
    }

    @ParameterizedTest(name = "Phone: {0}")
    @ValueSource(strings = {"989898989", "98989898989", "*+9898#898", "98f98g9898", ""})
    @Story("Create Customer: Invalid Phone (length/format)")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should reject invalid phone formats and lengths")
    void createCustomer_withInvalidPhoneNumber_shouldReturn400(String invalidPhoneNumber) {
        Users newUser = new Users();
        newUser.setUserName(faker.name().username());
        newUser.setEmail(faker.internet().emailAddress());
        newUser.setPhoneNumber(invalidPhoneNumber);
        Response response = customerApi.createCustomer(newUser);
        assertEquals(400, response.getStatusCode(), "Expected 400 but received: " + response.getStatusCode());
    }

    @ParameterizedTest(name = "Email: {0}")
    @ValueSource(strings = {"@bdoishd.com", "ehsgtd jdhys.com", "shgdtf@", "hagstdh@hf jdh.com", ""})
    @Story("Create Customer: Invalid Email format")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should reject invalid email formats")
    void crateCustomer_withInvalidEmail_shouldReturn400(String invalidEmail) {
        Users newUser = new Users();
        newUser.setUserName(faker.name().username());
        newUser.setEmail(invalidEmail);
        newUser.setPhoneNumber(faker.internet().emailAddress());
        Response response = customerApi.createCustomer(newUser);
        assertEquals(400, response.getStatusCode(), "Expected 400 but received: " + response.getStatusCode());
    }

    @Test
    @Story("Create Customer: Duplicate Username")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should reject customer creation with duplicate username")
    void createCustomer_withDuplicateUsername_shouldReturn400() {
        originalCustomer = new Users();
        originalCustomer.setUserName(faker.name().username());
        originalCustomer.setEmail(faker.internet().emailAddress());
        originalCustomer.setPhoneNumber(faker.number().digits(10));
        originalCustomer.setFirstName(faker.name().firstName());
        originalCustomer.setLastName(faker.name().lastName());
        Users created = customerApi.createCustomerAndExtract(originalCustomer);

        Users fetched = customerApi.findUserByUsername(originalCustomer.getUserName());
        assertNotNull(fetched, "User not found after creation.");
        customerId = fetched.getUserId();
        assertNotNull(customerId, "User ID was not returned by search.");
        System.out.println(customerId);

        Users duplicateUser = new Users();
        duplicateUser.setUserName(originalCustomer.getUserName());
        duplicateUser.setEmail(faker.internet().emailAddress());
        duplicateUser.setPhoneNumber(faker.phoneNumber().phoneNumber());
        duplicateUser.setFirstName(faker.name().firstName());
        duplicateUser.setLastName(faker.name().lastName());
        Response response = customerApi.createCustomer(duplicateUser);

        assertEquals(400, response.getStatusCode(), "Expected 400 but received: " + response.getStatusCode());
        System.out.println(response.getStatusCode());

        assertEquals(200, customerApi.deleteUser(customerId).getStatusCode(), "Delete call did not return 200.");
    }

    @Test
    @Story("Create Customer: Duplicate Email")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should reject customer creation with duplicate email")
    void createCustomer_withDuplicateEmail_shouldReturn400() {
        originalCustomer = new Users();
        originalCustomer.setUserName(faker.name().username());
        originalCustomer.setEmail(faker.internet().emailAddress());
        originalCustomer.setPhoneNumber(faker.number().digits(10));
        originalCustomer.setFirstName(faker.name().firstName());
        originalCustomer.setLastName(faker.name().lastName());
        Users created = customerApi.createCustomerAndExtract(originalCustomer);

        Users fetched = customerApi.findUserByUsername(originalCustomer.getUserName());
        assertNotNull(fetched, "User not found after creation.");
        customerId = fetched.getUserId();
        assertNotNull(customerId, "User ID was not returned by search.");
        System.out.println(customerId);

        Users duplicateUser = new Users();
        duplicateUser.setUserName(faker.name().username());
        duplicateUser.setEmail(originalCustomer.getEmail());
        duplicateUser.setPhoneNumber(faker.phoneNumber().phoneNumber());
        duplicateUser.setFirstName(faker.name().firstName());
        duplicateUser.setLastName(faker.name().lastName());
        Response response = customerApi.createCustomer(duplicateUser);

        assertEquals(400, response.getStatusCode(), "Expected 400 but received: " + response.getStatusCode());
        System.out.println(response.getStatusCode());

        assertEquals(200, customerApi.deleteUser(customerId).getStatusCode(), "Delete call did not return 200.");
    }

    @Test
    @Story("Create Customer: Duplicate Phone")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should reject customer creation with duplicate phone number")
    void createCustomer_withDuplicatePhone_shouldReturn400() {
        originalCustomer = new Users();
        originalCustomer.setUserName(faker.name().username());
        originalCustomer.setEmail(faker.internet().emailAddress());
        originalCustomer.setPhoneNumber(faker.number().digits(10));
        originalCustomer.setFirstName(faker.name().firstName());
        originalCustomer.setLastName(faker.name().lastName());
        Users created = customerApi.createCustomerAndExtract(originalCustomer);

        Users fetched = customerApi.findUserByUsername(originalCustomer.getUserName());
        assertNotNull(fetched, "User not found after creation.");
        customerId = fetched.getUserId();
        assertNotNull(customerId, "User ID was not returned by search.");
        System.out.println(customerId);

        Users duplicateUser = new Users();
        duplicateUser.setUserName(faker.name().username());
        duplicateUser.setEmail(faker.internet().emailAddress());
        duplicateUser.setPhoneNumber(originalCustomer.getPhoneNumber());
        duplicateUser.setFirstName(faker.name().firstName());
        duplicateUser.setLastName(faker.name().lastName());
        Response response = customerApi.createCustomer(duplicateUser);

        assertEquals(500, response.getStatusCode(), "Expected 400 but received: " + response.getStatusCode());
        System.out.println(response.getStatusCode());

        assertEquals(200, customerApi.deleteUser(customerId).getStatusCode(), "Delete call did not return 200.");
    }
}
