package com.smartgarage.api;

import com.smartgarage.api.models.ClientCarRequest;
import com.testframework.core.BaseApiService;
import io.restassured.response.Response;

public class ClientCarsApi extends BaseApiService {

    public ClientCarsApi() {
        super("");
    }

    public Response createNewClientCar(ClientCarRequest body, int userId) {
        return post("/api/users/" + userId + "/client-cars", body)
                .then()
                .extract().response();
    }

    public Response createNewClientCarAndExtract(ClientCarRequest body, int userId) {
        return post("/api/users/" + userId + "/client-cars", body).then().extract().response();
    }

    public Response addServiceToClientCar(int clientCarId) {
        return post("{id}/services/1" + clientCarId, new ClientCarRequest());
    }

    public Response updateClientCar(ClientCarRequest body, int clientCarId) {
        return put("/api/client-cars/" + clientCarId, body).then().extract().response();
    }

}
