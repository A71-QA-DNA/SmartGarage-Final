package web.entities;

import com.smartgarage.api.CustomerApi;
import com.smartgarage.api.models.Users;
import com.testframework.PropertiesManager;
import com.testframework.core.BaseWebTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Administrative Part")
@Feature("Employees must be able to filter the customers by name, email, phone number, vehicle (model or make) or visits in range (a visit between two dates).")
@Tag("system")
public class FilterCustomersTests extends BaseWebTest {

    private CustomerApi customerApi;
    private static Users createdUser;

    @BeforeEach
    public void setup() {
        customerApi = new CustomerApi();
        createdUser = new Users();
        createdUser.setUserName(faker.name().username());
        createdUser.setEmail(faker.internet().emailAddress());
        createdUser.setPhoneNumber(faker.number().digits(10));
        createdUser.setFirstName(faker.name().firstName());
        createdUser.setLastName(faker.name().lastName());
        customerApi.createCustomerAndExtract(createdUser);
        createdUser = customerApi.findUserByUsername(createdUser.getUserName());
        loginPage.navigate();
        loginPage.loginAs(PropertiesManager.getConfigProperties().getProperty("adminUsername"),
                PropertiesManager.getConfigProperties().getProperty("adminPassword"));

    }

    @AfterEach
    public void teardown() {
        if (createdUser.getUserId() != null) {
            customerApi.deleteUser(createdUser.getUserId());
        }
        loginPage.logout();
    }

    private WebElement searchElements(String username, String email, String phone, LocalDate dateFrom, LocalDate dateTo) {
        allUsersPage.navigate();

        if (username != null && !username.isEmpty()) {
            allUsersPage.typeUsername(username);
        }

        if (email != null && !email.isEmpty()) {
            allUsersPage.typeEmail(email);
        }

        if (phone != null && !phone.isEmpty()) {
            allUsersPage.typePhone(phone);
        }

        if (dateFrom != null) {
            allUsersPage.typeVisitDateFrom(dateFrom);
        }

        if (dateTo != null) {
            allUsersPage.typeVisitDateTo(dateTo);
        }

        allUsersPage.clickSearch();

        List<WebElement> rows = allUsersPage.getResultRows();
        assertFalse(rows.isEmpty(), "Expected at least one row in results");
        return rows.get(0);
    }

    private void assertRowMatchesUser(WebElement row, Users expected) {
        String actualUsername = allUsersPage.extractUsername(row);
        String actualEmail = allUsersPage.extractEmail(row);
        String actualPhone = allUsersPage.extractPhone(row);

        assertTrue(expected.getUserName().contains(actualUsername), "Username must match");
        assertTrue(expected.getEmail().contains(actualEmail), "Email must match");
        assertTrue(expected.getPhoneNumber().contains(actualPhone), "Phone must match");
    }

    @Test
    @DisplayName("Admin should be able to filter customers by username")
    public void filterCustomersByUsername() {
        WebElement first = searchElements(createdUser.getUserName(), null, null, null, null);
        assertRowMatchesUser(first, createdUser);
    }

    @Test
    @DisplayName("Admin should be able to filter customers by username and email")
    public void filterCustomersByUsernameAndEmail() {
        WebElement first = searchElements(createdUser.getUserName(), createdUser.getEmail(), null, null, null);
        assertRowMatchesUser(first, createdUser);
    }

    @Test
    @DisplayName("Admin should be able to filter customers by contains username")
    public void filterCustomerByContainsUsername() {
        String contains = createdUser.getUserName().substring(0, 4);
        WebElement first = searchElements(contains, null, null, null, null);

        assertRowMatchesUser(first, createdUser);
    }

    @Test
    @DisplayName("Admin should be able to filter customers by contains email")
    public void filterCustomerByContainsEmail() {
        String email = createdUser.getEmail().split("@")[0];
        WebElement first = searchElements(null, email, null, null, null);

        assertRowMatchesUser(first, createdUser);
    }

    @Test
    @DisplayName("Admin should be able to filter customers by contains phone number")
    public void filterCustomerByContainsPhoneNumber() {
        String phone = createdUser.getPhoneNumber();
        WebElement first = searchElements(null, null, phone,null, null);
        assertRowMatchesUser(first, createdUser);
    }

    @Test
    @DisplayName("Admin should be able to filter customers by multiple filters")
    public void filterByMultiple_AND() {
        String username = "felix_jackson";
        String carBrand = "Audi";
        LocalDate visitedBefore = LocalDate.parse("2024-01-01");

        allUsersPage.navigate();
        allUsersPage.typeUsername(username);
        allUsersPage.typeVisitDateTo(visitedBefore);
        allUsersPage.clickSearch();

        List<WebElement> rows = allUsersPage.getResultRows();
        assertFalse(rows.isEmpty(), "Expected at least one row in results");

        WebElement row = rows.get(0);
        String actualUsername = allUsersPage.extractUsername(row);
        List<LocalDate> serviceDates = allUsersPage.getServiceDates();
        List<String> vehicleBrands = allUsersPage.getVehicleBrands();


        assertEquals(username, actualUsername, "Username must match");
        assertTrue(serviceDates.stream().allMatch(date -> date.isBefore(visitedBefore)), "Service dates must be before 2024-01-01");
        assertTrue(vehicleBrands.contains(carBrand), "Vehicle brand must contain Audi");
    }
}
