package api.services;

import com.github.javafaker.Faker;
import com.smartgarage.api.ServicesApi;
import com.smartgarage.api.models.ServiceRequest;
import com.smartgarage.api.models.ServiceResponse;
import com.smartgarage.api.utils.ServiceDbUtils;
import com.testframework.core.BaseApiTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@Epic("Service API")
@Feature("CRUD & Filtering")
@Tag( "integration")
@Tag("services-api")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceApiTests extends BaseApiTest {

    private ServicesApi api;
    private final Faker faker = new Faker();
    private Integer createdId;
    private String createdName;

    @BeforeEach
    public void setup() {
        api = new ServicesApi();
    }

    @AfterEach
    void cleanup() {
        if (createdId != null) {
            ensureBaseService(createdId);
            api.delete(createdId);
            createdId = null;
            createdName = null;
        }
    }

    private ServiceResponse createService(int price) {
        ServiceRequest req = ServiceRequest.builder()
                .name(faker.commerce().productName())
                .price(price)
                .build();
        ServiceResponse res = api.createNewService(req);
        createdId = res.getId();
        createdName = res.getName();
        return res;
    }

    private void ensureBaseService(int serviceId) {
        ServiceDbUtils.setBaseService(serviceId, 1);
    }


    @ParameterizedTest(name = "Create service with price={0}")
    @ValueSource(ints = {0, 1, 10, 500})
    @Story("Create Service with valid price")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Create Service with valid price")
    void createService_withValidPrice(int price) {
        ServiceResponse res = createService(price);

        Assertions.assertNotNull(res.getId());
        Assertions.assertEquals(price, res.getPrice());
        Assertions.assertEquals(createdName, res.getName());
    }

    @Test
    @Story("Create Service with invalid price")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Create service with negative price → 400")
    void createService_withNegativePrice_shouldFail() {
        ServiceRequest req = ServiceRequest.builder()
                .name(faker.commerce().productName())
                .price(-1)
                .build();

        Response res = api.createService(req);
        Assertions.assertEquals(400, res.statusCode());
    }

    @Test
    @Story("Update Service")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Update service updates price")
    void updateServicePrice() {
        ServiceResponse createdService = createService(100);
        ensureBaseService(createdService.getId());


        ServiceRequest update = ServiceRequest.builder()
                .name(createdName)
                .price(200)
                .build();
        ServiceResponse updated = api.update(createdId, update);

        Assertions.assertEquals(200, updated.getPrice());
        Assertions.assertEquals(createdName, updated.getName());
    }

    @Test
    @Story("Delete Service")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("DELETE service by id")
    public void deleteService() {
        ServiceResponse created = createService(50);

        ensureBaseService(created.getId());

        Response res = api.delete(created.getId());
        Assertions.assertEquals(200, res.statusCode());

        createdId = null;
        createdName = null;
    }
}
