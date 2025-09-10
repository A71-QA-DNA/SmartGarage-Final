package com.testframework.core;

import com.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Properties;

public class RequestSpecFactory {

    private static final Properties CFG = PropertiesManager.getConfigProperties();
    private static final String BASE_URL = CFG.getProperty("smartGarageUrl");
    private static final String USERNAME = CFG.getProperty("adminUsername");
    private static final String PASSWORD = CFG.getProperty("adminPassword");

    public static RequestSpecification newBaseRequest() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().basic(USERNAME, PASSWORD);
    }

    public static RequestSpecification forService(String servicePath) {
        String normalized = servicePath.startsWith("/") ? servicePath : "/" + servicePath;
        return newBaseRequest().basePath(normalized);
    }
}
