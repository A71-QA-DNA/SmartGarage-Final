package com.testframework.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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