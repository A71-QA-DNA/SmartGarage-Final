package web.services;

import com.testframework.core.BaseWebTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ServicesTests extends BaseWebTest {

    private static final int TEST_SERVICE_ID = 25;

    @BeforeEach
    public void beforeEach() {
        loginPage.navigate();
        loginPage.loginAs("felix_jackson", "password123%D");
    }

    @AfterEach
    public void afterEach() {
        loginPage.logout();
    }


    @ParameterizedTest(name = "Edit service price → new value={0}, expectedSuccess={1}")
    @ValueSource(strings = {"00.00", "01.00", "-1",""})
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
}
