package web.vehicles;

import com.testframework.core.BaseWebTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.smartgarage.data.TestData.*;

@Epic("Vehicles")
@Feature("Successfully create client car with valid vehicle data")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("system")
public class ClientCarsTests extends BaseWebTest {

    @BeforeEach
    public void beforeEach() {
        loginPage.navigate();
        loginPage.loginAs(ADMIN_USERNAME, ADMIN_PASSWORD);
        adminPanelPage.navigate();
        clientCarsPage.navigate();
    }

    @AfterEach
    public void afterEach() {
        loginPage.logout();
    }

    @Test
    @Order(1)
    @DisplayName("Invalid VIN - 16 characters")
    @Story("VIN required when creating a vehicle and must be 17 characters long string")
    void unsuccessfully_createClientCar_shortVIN() {

        clientCarsPage.fillVehicleForm(
                INVALID_VIN_SHORT,
                generateVin(17),
                VALID_OWNER,
                VALID_MODEL,
                VALID_ENGINE_TYPE,
                VALID_YEAR);
        clientCarsPage.clickAddClientButton();

        assertTrue(clientCarsPage.isErrorMessageDisplayed(),
                "Error message is displayed for short VIN: ");
    }

    @Test
    @Order(2)
    @DisplayName("Invalid VIN - 18 characters")
    @Story("VIN required when creating a vehicle and must be 17 characters long string")
    void unsuccessfully_createClientCar_longVIN() {

        clientCarsPage.fillVehicleForm(
                INVALID_VIN_LONG,
                generateRandomLicensePlate(),
                VALID_OWNER,
                VALID_MODEL,
                VALID_ENGINE_TYPE,
                VALID_YEAR);
        clientCarsPage.clickAddClientButton();

        assertTrue(clientCarsPage.isErrorMessageDisplayed()
                , "Error message is displayed for long VIN: ");
    }

    @ParameterizedTest(name = "Invalid Bulgarian License Plate: {0}")
    @Order(3)
    @ValueSource(strings = {"A1234A", "CB4321B", "INVALID123", "12345"})
    @Story("License plate required when creating a vehicle and must be valid Bulgarian license plate")
    void unsuccessfully_createClientCar_invalidLicensePlate(String invalidPlate) {
        clientCarsPage.fillVehicleForm(generateVin(17), invalidPlate, VALID_OWNER,
                VALID_MODEL, VALID_ENGINE_TYPE, VALID_YEAR);
        clientCarsPage.clickAddClientButton();

        assertTrue(clientCarsPage.isErrorMessageDisplayed(),
                "Error message is displayed for invalid license plate: " + invalidPlate);
    }

    @ParameterizedTest(name = "Invalid Model: {0}")
    @Order(4)
    @ValueSource(strings = {"R", "This model name has exactly fifty one characters xx", "This model name is definitely way too long and exceeds fifty characters limit"})
    @Story("Model required when creating a vehicle and must be between 2 and 50 characters")
    void unsuccessfully_createClientCar_invalidModel(String invalidModel) {
        clientCarsPage.fillVehicleForm(generateVin(17), generateRandomLicensePlate(), VALID_OWNER,
                invalidModel, VALID_ENGINE_TYPE, VALID_YEAR);
        clientCarsPage.clickAddClientButton();

        assertTrue(clientCarsPage.isErrorMessageDisplayed(),
                "Error message is displayed for invalid model: " + invalidModel);
    }

    @ParameterizedTest(name = "Invalid year of creation: {0}")
    @Order(5)
    @ValueSource(strings = {"1886", "1885", "#100", "-1202", "202.4", "12345"})
    @DisplayName("Invalid year of creation")
    @Story("Year of creation required when creating a vehicle and must be a positive whole number larger than 1886")
    void unsuccessfully_createClientCar_invalidYear(String invalidYear) {
        clientCarsPage.fillVehicleForm(generateVin(17), generateRandomLicensePlate(), VALID_OWNER,
                VALID_MODEL, VALID_ENGINE_TYPE, invalidYear);
        clientCarsPage.clickAddClientButton();

        assertTrue(clientCarsPage.isErrorMessageDisplayed(),
                "Error message is displayed for year of creation: " + invalidYear);
    }

    @Test
    @Order(6)
    @DisplayName("Employee successfully creates a new client car")
    @Story("Employees must be able to create a new vehicle for a customer")
    void successfully_createClientCar_validData() {
        clientCarsPage.fillVehicleForm(generateVin(17), generateRandomLicensePlate(), VALID_OWNER,
                VALID_MODEL, VALID_ENGINE_TYPE, VALID_YEAR);
        clientCarsPage.clickAddClientButton();

//        assertTrue(clientCarsPage.isFormCleared(),
//                "Data fields are cleared");
        assertFalse(clientCarsPage.isErrorMessageDisplayed(),
                "Vehicle creation succeeds with all valid data");
    }

    @Test
    @Order(7)
    @DisplayName("Filter vehicles by owner first name")
    @Story("Employees are able to filter the vehicles by owner's first name")
    void successfully_filterByOwnerFirstName() {
        clientCarsPage.selectSortBy(SORT_BY_FIRST_NAME);
        clientCarsPage.searchForVehicle(SEARCH_OWNER_FIRSTNAME);

        assertTrue(clientCarsPage.isVehicleDisplayed(),
                "At least one vehicle should be displayed after filtering by first name");

        assertFalse(clientCarsPage.getFirstVehicleVin().isEmpty(),
                "First vehicle VIN should not be empty");
    }

    @Test
    @Order(8)
    @DisplayName("Filter vehicles by owner username")
    @Story("Employees are able to filter the vehicles by owner's username")
    void successfully_filterByOwnerUsername() {
        clientCarsPage.selectSortBy(SORT_BY_USERNAME);
        clientCarsPage.searchForVehicle(SEARCH_OWNER_USERNAME);

        assertTrue(clientCarsPage.isVehicleDisplayed(),
                "At least one vehicle should be displayed after filtering by username");

        assertFalse(clientCarsPage.getFirstVehicleVin().isEmpty(),
                "First vehicle VIN should not be empty");
    }

    @ParameterizedTest(name = "Filter order: {0}")
    @Order(9)
    @ValueSource(strings = {"asc", "desc"})
    @DisplayName("Filter vehicles with different sort orders")
    @Story("Employees are able to pick the order of the filter - ascending/descending")
    void successfully_filterWithSortOrder(String sortOrder) {
        clientCarsPage.selectSortBy(SORT_BY_FIRST_NAME);
        clientCarsPage.selectSortOrder(sortOrder);
        clientCarsPage.searchForVehicle(SEARCH_OWNER_FIRSTNAME);

        assertTrue(clientCarsPage.isVehicleDisplayed(),
                "At least one vehicle should be displayed with " + sortOrder + " sort order");

        assertFalse(clientCarsPage.getFirstVehicleVin().isEmpty(),
                "First vehicle VIN should not be empty when sorted " + sortOrder);
    }

    @Test
    @Order(10)
    @DisplayName("Filter vehicles by first name in ascending order")
    @Story("Employees must be able to filter the vehicles by owner")
    void successfully_filterByFirstNameAscending() {
        clientCarsPage.selectSortBy(SORT_BY_FIRST_NAME);
        clientCarsPage.selectSortOrder(SORT_ORDER_ASC);
        clientCarsPage.searchForVehicle(SEARCH_OWNER_FIRSTNAME);

        assertTrue(clientCarsPage.isVehicleDisplayed(),
                "At least one vehicle should be displayed when filtering by first name in ascending order");

        assertFalse(clientCarsPage.getFirstVehicleVin().isEmpty(),
                "First vehicle VIN should not be empty when sorted by first name ascending");
    }

    @Test
    @Order(11)
    @DisplayName("Filter vehicles by username in descending order")
    @Story("Employees must be able to filter the vehicles by owner")
    void successfully_filterByUsernameDescending() {
        clientCarsPage.selectSortBy(SORT_BY_USERNAME);
        clientCarsPage.selectSortOrder(SORT_ORDER_DESC);
        clientCarsPage.searchForVehicle(SEARCH_OWNER_USERNAME);

        assertTrue(clientCarsPage.isVehicleDisplayed(),
                "At least one vehicle should be displayed when filtering by username in descending order");

        // Optional: Also verify the VIN is not empty
        assertFalse(clientCarsPage.getFirstVehicleVin().isEmpty(),
                "First vehicle VIN should not be empty when sorted by username descending");
    }

    @Test
    @Order(12)
    @DisplayName("Browse all customer vehicles")
    @Story("Employees are able to browse all customer vehicles")
    void successfully_browseCustomerVehicles() {
        clientCarsPage.selectSortBy(SORT_BY_USERNAME);
        clientCarsPage.selectSortOrder(SORT_ORDER_DESC);
        clientCarsPage.searchForVehicle(SEARCH_OWNER_USERNAME);

        // Assert that at least one vehicle is displayed after filtering
        assertTrue(clientCarsPage.isVehicleDisplayed(),
                "At least one vehicle should be displayed when filtering by first name in ascending order");

        // Optional: Also verify the VIN is not empty
        assertFalse(clientCarsPage.getFirstVehicleVin().isEmpty(),
                "First vehicle VIN should not be empty when sorted by first name ascending");
    }

    @Test
    @Order(13)
    @DisplayName("Update customer vehicle")
    @Story("Employees are able to update all customer vehicles")
    void successfully_updateCustomerVehicle() {
        // First create a vehicle to update
        clientCarsPage.fillVehicleForm(generateVin(17), generateRandomLicensePlate(), VALID_OWNER,
                VALID_MODEL, VALID_ENGINE_TYPE, VALID_YEAR);
        clientCarsPage.clickAddClientButton();

        // Click edit on the vehicle
        clientCarsPage.clickEditButton();

        // Update vehicle details
        clientCarsPage.fillVehicleForm(generateVin(17), generateRandomLicensePlate(), VALID_OWNER,
                UPDATED_MODEL, UPDATED_ENGINE_TYPE, UPDATED_YEAR);
        clientCarsPage.clickSaveButton();

        assertFalse(clientCarsPage.isErrorMessageDisplayed(),
                "Vehicle update should succeed without errors");
    }
}
