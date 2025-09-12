package com.smartgarage.api;

import com.smartgarage.api.models.Users;
import com.testframework.core.BaseApiService;
import io.restassured.response.Response;

public class CustomerApi extends BaseApiService {

    public CustomerApi() {
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

    public Users createCustomerAndExtract(Users requestBody) { //da
        return postAndExtract("/customers", requestBody, 200, Users.class);
    }

    public Response createCustomer(Users requestBody) {
        return post("/customers", requestBody);
    }

    public Response deleteUser(int userId) {
        return delete("/" + userId);
    }

    public Users findUserByUsername(String username) {
        Users[] results = request()
                .queryParam("username", username)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .as(Users[].class);

        if (results == null || results.length == 0) {
            return null;
        }
        return results[0];
    }

    public Response userSelfUpdate(int userId, String username, String password, Users body) {
        return request()
                .auth().preemptive().basic(username, password)
                .body(body)
                .when()
                .put("/" + userId);
    }
}
