package com.smartgarage.api;


import com.smartgarage.api.models.ServiceResponse;
import com.smartgarage.api.models.ServiceRequest;
import com.testframework.core.BaseApiService;
import io.restassured.response.Response;

import static io.restassured.http.ContentType.JSON;

public class ServicesApi extends BaseApiService {

    public ServicesApi() {
        super("/api/services");
    }

    public ServiceResponse createNewService(ServiceRequest service) {
        return postAndExtract("", service, ServiceResponse.class);
    }

    public Response delete(int serviceId) {
        return delete("/" + serviceId);
    }

    public Response createService(ServiceRequest serviceRequest) {
        return post("", serviceRequest);
    }

    public ServiceResponse update(Integer serviceId, ServiceRequest request) {
        return request()
                .contentType(JSON)
                .body(request)
                .when()
                .put("/{id}", serviceId)
                .then()
                .extract()
                .as(ServiceResponse.class);
    }
}
