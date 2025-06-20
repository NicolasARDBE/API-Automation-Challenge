package com.inter2025api.config;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClientFactory {

    public static RequestSpecification createRequest() {
        RestAssuredConfig.configure();
        return RestAssured.given().log().all();
    }
}