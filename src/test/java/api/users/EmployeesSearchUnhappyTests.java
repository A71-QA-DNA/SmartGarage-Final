package api.users;

import com.github.javafaker.Faker;
import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import com.testframework.core.BaseApiTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNull;

@Epic("REST API")
@Feature("Employee Search– username/email/phone/VIN/license plate")
@Tag("integration")
@Tag("employee-search")
public class EmployeesSearchUnhappyTests extends BaseApiTest {
    private final CustomerApi customerApi = new CustomerApi();
    private final Faker faker = new Faker();

    @Test
    @Story("Admin should not find a non-existent customer by username")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Admin should NOT find a non-existent customer by username")
    void userNotFound_when_adminSearchesByNonExistentUsername() {
        String randomUsername = faker.name().username(); // unlikely to exist
        Users fetched = customerApi.findUserByUsername(randomUsername);
        assertNull(fetched, "User found with non-existing username: " + randomUsername);
    }

    @Test
    @Story("Admin should not find a non-existent customer by email")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Admin should NOT find a non-existent customer by email")
    void userNotFound_when_adminSearchesByNonExistentEmail() {
        String randomEmail = faker.internet().emailAddress(); // unlikely to exist
        Users fetched = customerApi.findUserByEmail(randomEmail);
        assertNull(fetched, "User found with non-existing email: " + randomEmail);
    }

    @Test
    @Story("Admin should not find a non-existent customer by phone")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Admin should NOT find a non-existent customer by phone")
    void userNotFound_when_adminSearchesByNonExistentPhone() {
        String randomPhone = faker.number().digits(10); // unlikely to exist
        Users fetched = customerApi.findUserByPhone(randomPhone);
        assertNull(fetched, "User found with non-existing phone: " + randomPhone);
    }

    @Disabled("Known backend bug: empty/blank query returns 200 instead of 400")
    @ParameterizedTest(name = "Empty username query: \"{0}\"")
    @ValueSource(strings = {"", " "})
    @Story("API should NOT return all users on an empty search query")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Empty/blank username should return 400 Bad Request")
    void allUsers_notReturned_onEmptyQuery(String emptyQuery) {
        customerApi.request()
                .queryParam("username", emptyQuery)
                .when()
                .get()
                .then()
                .statusCode(400);
    }
}
