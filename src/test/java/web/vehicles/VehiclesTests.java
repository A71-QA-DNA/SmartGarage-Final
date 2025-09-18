package web.vehicles;

import com.testframework.core.BaseWebTest;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

@Epic("Entities")
@Tag("system")

public class VehiclesTests extends BaseWebTest {

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


}
