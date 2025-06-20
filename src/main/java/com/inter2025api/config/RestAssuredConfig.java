package com.inter2025api.config;

import com.inter2025api.utils.ConfigUtil;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConfig {

    private static final String BASE_URI = ConfigUtil.getProperty("base.url");

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType("application/json")
                .build();
    }

    public static void configure() {
        RestAssured.requestSpecification = getRequestSpecification();
    }
}