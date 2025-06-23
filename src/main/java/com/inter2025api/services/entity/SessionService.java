package com.inter2025api.services.entity;

import io.restassured.response.Response;

public interface SessionService {
    Response requestToken();
    Response createSessionLogin(String requestToken);
    Response createSession(String requestToken);
}