package com.smartgarage.api;

import com.smartgarage.api.models.Users;
import com.testframework.core.BaseApiService;
import io.restassured.response.Response;

public class CustomerApi extends BaseApiService {

    public CustomerApi() {
        super("/api/users");
    }

    public Users getUserByUsername(String username) {
        return request()
                .queryParam("username", username)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .as(Users.class);
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

    public Users createCustomerAndExtract(Users requestBody) {
        return postAndExtract("/customers", requestBody, 200, Users.class);
    }

    public Response createCustomer(Users requestBody) {
        return post("/customers", requestBody);
    }

    public Response deleteUser(int userId) {
        return delete("/" + userId);
    }
}
