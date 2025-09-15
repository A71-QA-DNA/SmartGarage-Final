package api.vehicles;

import com.github.javafaker.Faker;
import com.smartgarage.api.VehicleApi;
import com.smartgarage.api.models.VehicleRequest;
import com.smartgarage.api.models.VehicleResponse;
import com.testframework.core.BaseApiTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Vehicle API")
@Feature("Validation & CRUD")
@Tag("vehicle_api")
public class VehiclesApiTests extends BaseApiTest {

    private static VehicleApi api;
    private Integer vehicleId;
    private static final Faker faker = new Faker();

    @BeforeAll
    public static void setup() {
        api = new VehicleApi();
    }

    @AfterEach
    public void cleanup() {
        if (vehicleId != null) {
            api.deleteVehicleById(vehicleId);
        }
    }

    private VehicleRequest buildVehicleRequest() {
        return VehicleRequest.builder()
                .brandName("Audi")
                .modelName(faker.name().firstName())
                .year(2019)
                .engineType("Petrol")
                .build();
    }

    private static Stream<Arguments> invalidNames() {
        return Stream.of(Arguments.of("", "Empty"),
                Arguments.of("a", "Too short"),
                Arguments.of("a".repeat(51), "Too long"),
                Arguments.of(" ", "Space only"));
    }


    @Test
    @Story("Create Vehicle")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify vehicle can be created independently with valid request")
    void createVehicle_validVehicle_returns200() {
        VehicleRequest vehicleRequest = buildVehicleRequest();

        VehicleResponse res = api.createVehicleAndGetById(vehicleRequest);

        assertEquals(vehicleRequest.getBrandName(), res.getBrand().getName());
        assertEquals(vehicleRequest.getModelName(), res.getModel().getName());
        assertEquals(vehicleRequest.getYear(), res.getYear().getYear());
        assertEquals(vehicleRequest.getEngineType(), res.getEngineType().getName());
    }

    @Test
    @Story("Get Vehicle By Id")
    @Severity(SeverityLevel.NORMAL)
    public void getVehicleById_withValidId_returns200() {
        VehicleRequest vehicleRequest = buildVehicleRequest();
        VehicleResponse created = api.createVehicleAndGetById(vehicleRequest);

        VehicleResponse response = api.getVehicleById(created.getId()).then().statusCode(200)
                .extract().as(VehicleResponse.class);
        vehicleId = created.getId();

        assertEquals(created.getId(), response.getId(),"Vehicle id should match");
    }

    @ParameterizedTest(name = "Brand {0} is not in whitelist")
    @ValueSource(strings = {"BMW", "Mercedes", "Volvo"})
    @Story("The brand must be between 2 and 50 symbols.")
    public void create_invalid_brand_not_in_whitelist_returns400(String brandName) {
        VehicleRequest vehicleRequest = buildVehicleRequest();
        vehicleRequest.setBrandName(brandName);
        Response res = api.createVehicle(vehicleRequest);

        assertEquals(400, res.statusCode(), "Invalid brand should return 400");
    }

    @ParameterizedTest(name = "Invalid model length {0}")
    @MethodSource("invalidNames")
    @Story("The model must be between 2 and 50 symbols.")
    public void createVehicle_with_invalidModel_length_returns400(String modelName) {
        VehicleRequest vehicleRequest = buildVehicleRequest();
        vehicleRequest.setModelName(modelName);
        Response res = api.createVehicle(vehicleRequest);

        assertEquals(400, res.statusCode(), "Invalid model length should return 400");
    }

    @ParameterizedTest(name = "Invalid engine type length {0}")
    @MethodSource("invalidNames")
    public void createVehicle_with_invalidEngineType_length_returns400(String engineType) {
        VehicleRequest vehicleRequest = buildVehicleRequest();
        vehicleRequest.setEngineType(engineType);
        Response res = api.createVehicle(vehicleRequest);

        assertEquals(400, res.statusCode(), "Invalid model length should return 400");
    }

    @ParameterizedTest(name = "Invalid year {0}")
    @ValueSource(ints = {-1, 0, 1885})
    @Story("The year of creation must be a positive whole number larger than 1886.")
    public void createVehicle_with_invalidYear_returns400(int year) {
        VehicleRequest vehicleRequest = buildVehicleRequest();
        vehicleRequest.setYear(year);
        Response res = api.createVehicle(vehicleRequest);

        assertEquals(400, res.statusCode(), "Invalid year should return 400");
    }

    @Test
    public void updateVehicle_withValidData_returns200() {
        VehicleRequest vehicleRequest = buildVehicleRequest();
        VehicleResponse created = api.createVehicleAndGetById(vehicleRequest);
        vehicleId = created.getId();

        vehicleRequest.setModelName(faker.commerce().productName());
        Response response = api.updateVehicle(created.getId(), vehicleRequest);

        assertEquals(200, response.statusCode(), "Update should return 200");
    }

    @Test
    @Story("The year of creation must be a positive whole number larger than 1886.")
    public void createVehicle_withFutureYear_returns400() {
        VehicleRequest vehicleRequest = buildVehicleRequest();
        vehicleRequest.setYear(2027);
        Response response = api.createVehicle(vehicleRequest);

        assertEquals(400, response.statusCode(), "Future year should return 400");
    }
}
