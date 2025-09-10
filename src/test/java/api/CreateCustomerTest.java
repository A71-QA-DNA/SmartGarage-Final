package api;

import com.github.javafaker.Faker;
import com.smartgarage.api.UsersApi;
import com.smartgarage.api.models.CustomerRequest;
import com.testframework.core.BaseApiTest;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Customer API")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class CreateCustomerTest extends BaseApiTest {

    private static final Faker faker = new Faker();
    private static final UsersApi api = new UsersApi();

    private static CustomerRequest validCustomer() {
        String username = faker.name().username().replaceAll("[^a-zA-Z0-9]", "").substring(0, 10);
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.number().digits(10);

        return CustomerRequest.builder()
                .username(username)
                .email(email)
                .phoneNumber(phoneNumber)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }


    @Test
    public void createCustomer_withValidData_returns200() {
        CustomerRequest customerRequest = validCustomer();
        CustomerRequest res = api.createCustomerAndExtract(customerRequest);

        assertEquals(customerRequest.getUsername(), res.getUsername(), "Username must match");
        assertEquals(customerRequest.getEmail(), res.getEmail(), "Email must match");
        assertEquals(customerRequest.getPhoneNumber(), res.getPhoneNumber(), "Phone number must match");
    }

    @Test
    public void createCustomer_withInvalidUsername_returns400() {
//        CustomerRequest customerRequest = validCustomer();
//        customerRequest.setUsername("a");
//
//        Response response = api.createCustomer(customerRequest);

//        assertEquals(400, response.statusCode(), "Invalid username should return 400");
        expect400StatusCode(request -> request.setUsername("a"), "Invalid username should return 400");
    }

    private void expect400StatusCode(Consumer<CustomerRequest> request, String message) {
        CustomerRequest customerRequest = validCustomer();
        request.accept(customerRequest);
        Response response = api.createCustomer(customerRequest);
        assertEquals(400, response.statusCode(), message);
    }
}
