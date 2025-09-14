package api.clientCars;

import com.github.javafaker.Faker;
import com.smartgarage.api.ClientCarsApi;
import com.smartgarage.api.models.ClientCarRequest;
import com.smartgarage.api.models.ClientCarResponse;
import com.testframework.core.BaseApiTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Client Cars API")
@Feature("CRUD & Validation")
public class ClientCarsTest extends BaseApiTest {

    private ClientCarsApi clientCarsApi;
    private static final Faker faker = new Faker();

    @BeforeEach

    public void before() {
        clientCarsApi = new ClientCarsApi();
    }

    private ClientCarRequest createNewClientCarRequest() {
        return ClientCarRequest.builder()
                .vin(generateVin(17))
                .licensePlate(generateRandomLicensePlate())
                .brandName("Audi")
                .modelName(faker.name().firstName())
                .year(2020)
                .engineType("Petrol")
                .build();
    }

    private static String generateVin(int n) {
        String alphabet = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(alphabet.charAt(faker.random().nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    public static String generateRandomLicensePlate() {
        final List<String> REGION_CODES = List.of(
                "A", "B", "CH", "Y", "TX", "H", "CC", "PP", "T", "P", "BT", "EB", "CT", "X", "K", "CM",
                "PB", "OB", "EH", "PA", "E", "KH", "PK", "CA", "C", "CB", "CO", "BP", "M", "BH"
        );

        final char[] VALID_SUFFIX = {
                'A', 'B', 'E', 'K', 'M', 'H', 'O', 'P', 'C', 'T', 'Y', 'X'
        };

        final Random RNG = ThreadLocalRandom.current();
        String region = REGION_CODES.get(RNG.nextInt(REGION_CODES.size()));
        String number = String.format("%04d", RNG.nextInt(10_000));
        char first = VALID_SUFFIX[RNG.nextInt(VALID_SUFFIX.length)];
        char second = VALID_SUFFIX[RNG.nextInt(VALID_SUFFIX.length)];
        return region + number + first + second;
    }

    @Test
    @Story("Each vehicle must have a license plate, VIN, year of creation, model, and brand.")
    @DisplayName("Create Client Car: Valid Payload")
    @Severity(SeverityLevel.CRITICAL)
    public void create_withValidPayload_returnsSuccess() {
        ClientCarRequest newClientCarRequest = createNewClientCarRequest();
        ClientCarResponse res = clientCarsApi.createNewClientCar(newClientCarRequest, 1).as(ClientCarResponse.class);

        Assertions.assertEquals(newClientCarRequest.getVin(), res.getVin(), "VIN must match");
        Assertions.assertEquals(newClientCarRequest.getBrandName(), res.getVehicle().getBrand().getName(), "Brand must match");
    }

    @Test
    @Story("Create Client Car: Min Field Boundary")
    @DisplayName("Create Client Car: Min Field Boundary")
    @Severity(SeverityLevel.CRITICAL)
    public void create_withMinFieldBoundary() {
        ClientCarRequest minFieldClientCarRequest = createNewClientCarRequest();
        minFieldClientCarRequest.setModelName("aa");
        minFieldClientCarRequest.setYear(1886);

        Response response = clientCarsApi.createNewClientCar(minFieldClientCarRequest, 1);

        Assertions.assertEquals(200, response.statusCode(), "Min field boundary should return 200");
    }

    @Test
    @Story("Create Client Car: Empty Brand Name")
    @DisplayName("Create Client Car: Empty Brand Name")
    @Severity(SeverityLevel.CRITICAL)
    public void create_withEmptyBrandName_returns400() {
        ClientCarRequest newClientCarRequest = createNewClientCarRequest();
        newClientCarRequest.setBrandName("");
        Response response = clientCarsApi.createNewClientCar(newClientCarRequest, 1);

        Assertions.assertEquals(400, response.statusCode(), "Empty brand name should return 400");
    }

    @Test
    @Story("Create Client Car: Duplicate Licence Plate")
    @DisplayName("Create Client Car: Duplicate Licence Plate")
    @Severity(SeverityLevel.CRITICAL)
    public void create_withDuplicateLicensePlate_returns400() {
        ClientCarRequest newClientCarRequest = createNewClientCarRequest();
        clientCarsApi.createNewClientCar(newClientCarRequest, 1);
        ClientCarRequest duplicate = createNewClientCarRequest();
        duplicate.setLicensePlate(newClientCarRequest.getLicensePlate());
        Response res = clientCarsApi.createNewClientCar(duplicate, 1);

        Assertions.assertEquals(409, res.getStatusCode());
    }

    @ParameterizedTest(name = "Invalid VIN {0}")
    @ValueSource(strings = {"2WBNJK7JWFXQT3AE", "2WBNJK7JWFXQT3AEUA"})
    public void create_withInvalidVin_returns400(String invalidVin) {
        ClientCarRequest newClientCarRequest = createNewClientCarRequest();
        newClientCarRequest.setVin(invalidVin);

        Response response = clientCarsApi.createNewClientCar(newClientCarRequest, 1);

        Assertions.assertEquals(400, response.statusCode(), "Invalid VIN should return 400");
    }

    @Test
    @Story("Create Client Car: Duplicate VIN")
    @Severity(SeverityLevel.CRITICAL)
    public void create_withDuplicateVin_returns400() {
        ClientCarRequest newClientCarRequest = createNewClientCarRequest();
        clientCarsApi.createNewClientCar(newClientCarRequest, 1);
        ClientCarRequest duplicate = createNewClientCarRequest();
        duplicate.setVin(newClientCarRequest.getVin());
        Response res = clientCarsApi.createNewClientCar(duplicate, 1);

        Assertions.assertEquals(409, res.statusCode(), "Duplicate VIN should return 400");
    }

    @ParameterizedTest(name = "Invalid year {0}")
    @ValueSource(ints = {-1, 0, 1885})
    @Story("The year of creation must be a positive whole number larger than 1886.")
    public void createClientCar_with_invalidYear_returns400(int year) {
        ClientCarRequest newClientCarRequest = createNewClientCarRequest();
        newClientCarRequest.setYear(year);
        Response res = clientCarsApi.createNewClientCar(newClientCarRequest, 1);

        assertEquals(400, res.statusCode(), "Invalid year should return 400");
    }

    @Test
    @Story("Update Client Car: Valid Payload")
    @DisplayName("Update Client Car: Valid Payload")
    public void updateClientCar_withValidData_returns200() {
        ClientCarRequest updatedCar = createNewClientCarRequest();
        Response clientCar = clientCarsApi.createNewClientCarAndExtract(updatedCar, 1);

        int carId = clientCar.as(ClientCarResponse.class).getId();
        String newLicensePlate = generateRandomLicensePlate();
        updatedCar.setLicensePlate(newLicensePlate);
        updatedCar.setVin(updatedCar.getVin());

        Response response = clientCarsApi.updateClientCar(updatedCar, carId);

        Assertions.assertEquals(200, response.statusCode(), "Update should return 200");

    }
}
