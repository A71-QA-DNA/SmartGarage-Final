package api;

import com.github.javafaker.Faker;
import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import com.testframework.core.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Customer API")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UsersAPITests extends BaseApiTest {

    private static final Faker faker = new Faker();
    private static final CustomerApi api = new CustomerApi();

    private static Users validCustomer() {
        String username = faker.name().username().substring(0, 10);
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.number().digits(10);

        return Users.builder()
                .userName(username)
                .email(email)
                .phoneNumber(phoneNumber)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }

    @Test
    @Step("1. Authenticate")
    void authenticated_canFetchUsers() {
        CustomerApi api = new CustomerApi();
        Response res = api.getAllUsers();
        assertEquals(200, res.statusCode(), "Authenticated request should return 200");
    }


    @Test
    public void createCustomer_withValidData_returns200() {
        Users users = validCustomer();
        Users res = api.createCustomerAndExtract(users);

        assertEquals(users.getUserName(), res.getUserName(), "Username must match");
        assertEquals(users.getEmail(), res.getEmail(), "Email must match");
        assertEquals(users.getPhoneNumber(), res.getPhoneNumber(), "Phone number must match");
    }

    @Test
    public void createCustomer_withInvalidUsername_returns400() {
        Users usersRequest = validCustomer();
        usersRequest.setUserName("a");

        Response response = api.createCustomer(usersRequest);

        assertEquals(400, response.statusCode(), "Invalid username should return 400");
    }

    @Test
    public void creatCustomer_WithExistingUsername_returns500() {
        Users users = api.createCustomerAndExtract(validCustomer());
        Users duplicate = validCustomer();
        duplicate.setUserName(users.getUserName());
        Response response = api.createCustomer(duplicate);
        assertEquals(500, response.statusCode(), "Duplicate username should return 500");
    }
}
