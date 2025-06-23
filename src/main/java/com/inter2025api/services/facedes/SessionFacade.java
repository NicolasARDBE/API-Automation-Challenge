package com.inter2025api.services.facedes;

import com.inter2025api.context.TestContext;
import com.inter2025api.services.entity.SessionService;
import com.inter2025api.services.impl.SessionImpl;

import io.restassured.response.Response;

public class SessionFacade {

    private final SessionService sessionService = new SessionImpl();
    private final TestContext testContext;

    public SessionFacade(TestContext testContext) {
        this.testContext = testContext;
    }

    public void requestToken() {
        Response response = sessionService.requestToken();
        System.out.println("Request Token Response: " + response.jsonPath().getString("request_token"));
        testContext.set("request_token", response.jsonPath().getString("request_token"));
    }

    public void createSessionLogin() {
        sessionService.createSessionLogin(testContext.get("request_token").toString());
    }

    public void createSession() {
        String requestToken = (String) testContext.get("request_token");
        Response response = sessionService.createSession(requestToken);
        System.out.println("Session ID: " + response.jsonPath().getString("session_id"));
        testContext.set("sessionId", response.jsonPath().getString("session_id"));
    }
}
