package api.users;

import com.github.javafaker.Faker;
import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Epic("REST API")
@Feature("Employee Search– username/email/phone/VIN/license plate")
public class EmployeesSearchUnhappyTests {
    private final CustomerApi customerApi = new CustomerApi();
    private final Faker faker = new Faker();
    private String userName;
    private String email;
    private String phone;

    @Test
    @Story("Admin should not find a non-existent customer by username")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Admin should not find a non-existent customer by username")
    void userNotFound_when_adminSearchesByNonExistentUsername() {
        userName = faker.name().username();
        Users fetched = customerApi.findUserByUsername(userName);
        assertNull(fetched, "User found with non-existing username.");
    }

    @Test
    @Story("Admin should not find a non-existent customer by email")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Admin should not find a non-existent customer by email ")
    void userNotFound_when_adminSearchesByNonExistentEmail() {
        email = faker.internet().emailAddress();
        Users fetched = customerApi.findUserByEmail(email);
        assertNull(fetched, "User found with non-existing email.");
    }

    @Test
    @Story("Admin should not find a non-existent customer by phone")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Admin should not find a non-existent customer by phone")
    void userNotFound_when_adminSearchesByNonExistentPhone() {
        phone = faker.number().digits(10);
        Users fetched = customerApi.findUserByPhone(phone);
        assertNull(fetched, "User found with non-existent phone.");
    }

    @ParameterizedTest(name = "Search Query: '{0}'")
    @ValueSource(strings = {"", " "})
    @Story("API should not return all users on an empty search query")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API should not return all users on an empty search query")
    void allUsers_notReturned_onEmptyQuery(String emptyQuery) {
        Users[] results = customerApi.request()
                .queryParam("username", emptyQuery)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .as(Users[].class);

        assertEquals(0, results.length, "API returned all users for an empty search query.");
    }

    @ParameterizedTest(name = "Username: {0}")
    @ValueSource(strings = {"a", "abcdefghijklmnopqrstuv"})
    @Story("API should reject invalid username length with a 400 Bad Request")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API should reject invalid username length with a 400 Bad Request")
    void apiRejects_invalidUsernameLength_with400(String invalidUsername) {
        customerApi.request()
                .queryParam("username", invalidUsername)
                .when()
                .get()
                .then()
                .statusCode(400);
    }

    @ParameterizedTest(name = "Email: {0}")
    @ValueSource(strings = {"@bdoishd.com", "ehsgtd jdhys.com", "shgdtf@", "hagstdh@hf jdh.com"})
    @Story("API should reject invalid email format with a 400 Bad Request")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API should reject invalid email format with a 400 Bad Request")
    void apiRejects_invalidEmailFormat_with400(String invalidEmail) {
        customerApi.request()
                .queryParam("email", invalidEmail)
                .when()
                .get()
                .then()
                .statusCode(400);
    }
}

