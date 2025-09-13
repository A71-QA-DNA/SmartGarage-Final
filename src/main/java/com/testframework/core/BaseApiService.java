package com.testframework.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseApiService {
    private final Gson serializer;
    private final String servicePath;

    public BaseApiService(String servicePath) {
        this.servicePath = servicePath;
        this.serializer = new GsonBuilder().setPrettyPrinting().create();
    }

    public RequestSpecification request() {
        return RequestSpecFactory.forService(servicePath);
    }

    protected Gson gson() {
        return serializer;
    }

    protected <T> T postAndExtract(String path, Object body, int expectedStatus, Class<T> type) {
        return request()
                .body(body)
                .when()
                .post(path)
                .then()
                .statusCode(expectedStatus)
                .extract()
                .as(type);
    }

    protected Response post(String path, Object body) {
        return request()
                .body(body)
                .when()
                .post(path);
    }

    public Response get(String path,String key, Object value) {
        if (key != null && value != null) {
            return request()
                    .queryParam(key, value)
                    .when()
                    .get(path);
        } else {
            return request().when().get(path);
        }
    }

    protected Response delete(String path) {
        return request()
                .when()
                .delete(path);
    }

    @BeforeAll
    public static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(
                new RequestLoggingFilter(LogDetail.METHOD),
                new RequestLoggingFilter(LogDetail.URI),
                new RequestLoggingFilter(LogDetail.BODY),
                new ResponseLoggingFilter(LogDetail.STATUS),
                new ResponseLoggingFilter(LogDetail.BODY)
        );
    }
}