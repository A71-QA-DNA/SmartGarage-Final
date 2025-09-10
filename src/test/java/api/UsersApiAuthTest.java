package api;

import com.smartgarage.api.UsersApi;
import com.testframework.core.BaseApiTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersApiAuthTest extends BaseApiTest {

    @Test
    @Step("1. Authenticate")
    void authenticated_canFetchUsers() {
        UsersApi api = new UsersApi();
        Response res = api.getAllUsers();
        assertEquals(200, res.statusCode(), "Authenticated request should return 200");
    }

}
