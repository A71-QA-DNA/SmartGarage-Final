package api.users;

import com.github.javafaker.Faker;
import com.smartgarage.api.models.Users;
import com.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.filter.log.LogDetail.ALL;

public class CreateCustomerTests {
    private static Faker faker;
    private Users originalUser;
    private Integer userId;
    private String userPassword;

    private static String adminUser;
    private static String adminPass;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8081";
        faker = new Faker();
        adminUser = PropertiesManager.getConfigProperties().getProperty("adminUsername");
        adminPass = PropertiesManager.getConfigProperties().getProperty("adminPassword");
    }

    @BeforeEach
    public void setupTestUser() {
        originalUser = new Users();
        originalUser.setUserName(faker.name().username());
        originalUser.setEmail(faker.internet().emailAddress());
        originalUser.setPhoneNumber(faker.number().digits(10));
        originalUser.setFirstName(faker.name().firstName());
        originalUser.setLastName(faker.name().lastName());

        Response createResponse = RestAssured.given()
                .auth().preemptive().basic(adminUser, adminPass)
                .spec(new RequestSpecBuilder().log(ALL).build())
                .contentType(ContentType.JSON)
                .body(originalUser)
                .post("/api/users/customers");

        Assertions.assertEquals(200, createResponse.getStatusCode(),
                "Unexpected status: " + createResponse.asString());

        userPassword = createResponse.jsonPath().getString("password");

        Response getResponse = RestAssured.given()
                .auth().preemptive().basic(adminUser, adminPass)
                .spec(new RequestSpecBuilder().log(ALL).build())
                .get("/api/users?username=" + originalUser.getUserName());

        System.out.println("Lookup response: " + getResponse.asString());

        userId = getResponse.jsonPath().getInt("[0].id");

        Assertions.assertNotNull(userId, "Could not resolve userId after creation.");
    }

    @AfterEach
    public void teardownTestUser() {
        if (userId != null) {
            RestAssured.given()
                    .auth().preemptive().basic(adminUser, adminPass)
                    .spec(new RequestSpecBuilder().log(ALL).build())
                    .delete("/api/users/" + userId);
        }
    }

    @Test
    @DisplayName("Should Create a Customer Successfully")
    public void customerSuccessfullyCreated() {
        Assertions.assertNotNull(userId);
        System.out.println("Customer id: " + userId);
    }

    @Test
    @DisplayName("Should Update a Customer's Phone Number")
    public void customerPhoneSuccessfullyUpdated() {
        Users updatedUser = new Users();
        updatedUser.setEmail(originalUser.getEmail());
        updatedUser.setPhoneNumber(faker.number().digits(10));
        updatedUser.setFirstName(originalUser.getFirstName());
        updatedUser.setLastName(originalUser.getLastName());

        Response updateResponse = RestAssured.given()
                .auth().preemptive().basic(originalUser.getUserName(), userPassword)
                .spec(new RequestSpecBuilder().log(ALL).build())
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .put("/api/users/" + userId);

        Assertions.assertEquals(200, updateResponse.getStatusCode(),
                "Update failed: " + updateResponse.asString());
    }

    @Test
    @DisplayName("Should Delete a Customer Successfully")
    public void customerSuccessfullyDeleted() {
        Response deleteResponse = RestAssured.given()
                .auth().preemptive().basic(adminUser, adminPass)
                .spec(new RequestSpecBuilder().log(ALL).build())
                .delete("/api/users/" + userId);

        Assertions.assertEquals(200, deleteResponse.getStatusCode(),
                "Delete failed: " + deleteResponse.asString());

        Response getResponse = RestAssured.given()
                .auth().preemptive().basic(adminUser, adminPass)
                .spec(new RequestSpecBuilder().log(ALL).build())
                .get("/api/users/" + userId);

        Assertions.assertEquals(404, getResponse.getStatusCode(),
                "Expected 404 after delete, got: " + getResponse.statusCode());

        userId = null;
    }
}
