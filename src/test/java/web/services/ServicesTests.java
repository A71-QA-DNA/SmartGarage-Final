package web.services;

import com.testframework.core.BaseWebTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Entities")
@Feature("Each service must have a price")
@Tag("system")
public class ServicesTests extends BaseWebTest {

    private static final String TEST_SERVICE_ID = "25";
    private static final String ADMIN_USERNAME = "felix_jackson";
    private static final String ADMIN_PASSWORD = "password123%D";

    @BeforeEach
    public void beforeAll() {
        loginPage.navigate();
        loginPage.loginAs(ADMIN_USERNAME, ADMIN_PASSWORD);
    }

    @AfterEach
    public void afterEach() {
        loginPage.logout();
    }


    @ParameterizedTest(name = "Edit service price → new value={0}, expectedSuccess={1}")
    @ValueSource(strings = {"00.00", "01.00", "-1"})
    @Story("Each service must have a price. The price must be a non-negative number.")
    public void whenEditingServicePrice_validateNonNegativeInput(String newPrice) {
        servicesPage.navigate();
        servicesPage.clickBrakeRepairLink();
        serviceOverviewPage.clickEdit(TEST_SERVICE_ID);
        serviceOverviewPage.enterPriceForService(TEST_SERVICE_ID, newPrice);
        serviceOverviewPage.clickSave(TEST_SERVICE_ID);

        if (newPrice.equals("-1") || newPrice.isEmpty()) {
            driver().navigate().refresh();
            assertNotEquals(newPrice, serviceOverviewPage.getServicePrice(TEST_SERVICE_ID));
        } else {
            String actualPrice = serviceOverviewPage.getServicePrice(TEST_SERVICE_ID);
            assertEquals(newPrice, actualPrice);
        }
    }

    @ParameterizedTest(name = "Create service with price={0} → expectSaved={1}")
    @ValueSource(strings = {"00.00", "01.00", "-1"})
    void addService_validatePrice_andCleanup(String price) {
        servicesPage.navigate();
        servicesPage.clickBrakeRepairLink();

        int before = serviceOverviewPage.getServiceCount();
        String name = "UI Test Service " + System.currentTimeMillis();

        serviceOverviewPage.clickAddService();
        serviceOverviewPage.enterNewService(name, price);
        serviceOverviewPage.clickSaveNewService();

        if (price.equals("-1")) {
            driver().navigate().refresh();
            assertEquals(before, serviceOverviewPage.getServiceCount());
        } else {
            String newId = serviceOverviewPage.findNewServiceId(name);

            int actual = serviceOverviewPage.getServiceCount();
            String expectedName = serviceOverviewPage.getServiceRowByName(newId);
            String expectedPrice = serviceOverviewPage.getServicePrice(newId);

            assertEquals(expectedName, name);
            assertEquals(expectedPrice, price);
            assertNotEquals(before, actual);
            serviceOverviewPage.deleteServiceById(newId);
        }

    }
}
