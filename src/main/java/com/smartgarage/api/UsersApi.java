package com.smartgarage.api;

import com.smartgarage.api.models.CustomerRequest;
import com.testframework.core.BaseApiService;
import io.restassured.response.Response;

public class UsersApi extends BaseApiService {

    public UsersApi() {
        super("/api/users");
    }

    public Response getAllUsers() {
        return request().when().get();
    }

    public Response getUserById(int userId) {
        return request().when().get("/" + userId);
    }

    public Response getOrdersForUser(int userId) {
        return request().when().get("/" + userId + "/orders");
    }

    public CustomerRequest createCustomerAndExtract(CustomerRequest requestBody) {
        return request()
                .body(requestBody)
                .when()
                .post("/customers")
                .then()
                .statusCode(200)
                .extract()
                .as(CustomerRequest.class);
    }

    public Response createCustomer(CustomerRequest requestBody) {
        return request()
                .body(requestBody)
                .when()
                .post("/customers");
    }
}
