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
    private RequestSpecification requestSpecification;

    public BaseApiService(String servicePath) {
        this.serializer = new GsonBuilder().setPrettyPrinting().create();
        this.requestSpecification = RequestSpecFactory.forService(servicePath);
    }

    public RequestSpecification request() {
        return requestSpecification;
    }

    protected Gson gson() {
        return serializer;
    }

    protected void setRequestSpecification(RequestSpecification req) {
        requestSpecification = req;
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