package com.inter2025api.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {

    private static final Logger logger = LogManager.getLogger(RestAssuredClient.class);

    public RestAssuredClient() {
    }

    public RestAssuredClient addHeader(RequestSpecification request, String key, String value) {
        request.header(key, value);
        return this;
    }

    public RestAssuredClient addQueryParam(RequestSpecification request, String key, String value) {
        request.queryParam(key, value);
        return this;
    }

    public RestAssuredClient addPathParam(RequestSpecification request, String key, String value) {
        request.pathParam(key, value);
        return this;
    }

    public RestAssuredClient addBody(RequestSpecification request, Object body) {
        request.body(body);
        return this;
    }

    public Response get(RequestSpecification request, String endpoint) {
        logRequest("GET", endpoint);
        Response response = request.get(endpoint);
        logResponse(response);
        return response;
    }

    public Response post(RequestSpecification request, String endpoint) {
        logRequest("POST", endpoint);
        Response response = request.post(endpoint);
        logResponse(response);
        return response;
    }

    public Response put(RequestSpecification request, String endpoint) {
        logRequest("PUT", endpoint);
        Response response = request.put(endpoint);
        logResponse(response);
        return response;
    }

    public Response delete(RequestSpecification request, String endpoint) {
        logRequest("DELETE", endpoint);
        Response response = request.delete(endpoint);
        logResponse(response);
        return response;
    }

    private void logRequest(String method, String endpoint) {
        logger.info("========== REQUEST ==========");
        logger.info("Request Method: {}", method);
        logger.info("Request Endpoint: {}", endpoint);
        logger.info("=============================");
    }

    private void logResponse(Response response) {
        logger.info("========== RESPONSE ==========");
        logger.info("Response Status Code: {}", response.getStatusCode());
        logger.info("Response Headers: {}", response.getHeaders());
        logger.info("Response Body: {}", response.getBody().asString());
        logger.info("==============================");
    }
}