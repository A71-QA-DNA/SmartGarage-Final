package com.smartgarage.api;

import com.smartgarage.api.models.VehicleRequest;
import com.smartgarage.api.models.VehicleResponse;
import com.testframework.core.BaseApiService;
import io.restassured.response.Response;

public class VehicleApi extends BaseApiService {


    public VehicleApi() {
        super("/api/vehicles");
    }

    public Response createVehicle(VehicleRequest body) {
        return post("", body);
    }

    public VehicleResponse createVehicleAndGetById(VehicleRequest body) {
        return post("", body)
                .then()
                .statusCode(200)
                .extract()
                .response().
                as(VehicleResponse.class);
    }

    public Response getVehicleById(int vehicleId) {
        return get("/" + vehicleId, null, null);
    }

    public Response updateVehicle(int vehicleId, VehicleRequest body) {
        return put("/" + vehicleId, body);
    }

    public Response deleteVehicleById(int vehicleId) {
        return delete("/" + vehicleId);
    }

    public Response getAllVehicles() {
        return get("", null, null);
    }
}
