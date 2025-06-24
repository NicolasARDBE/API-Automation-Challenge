package com.inter2025api.services.facedes;

import com.inter2025api.context.TestContext;
import com.inter2025api.services.entity.SessionService;
import com.inter2025api.services.impl.SessionImpl;
import com.inter2025api.utils.Constants;

import io.restassured.response.Response;

public class SessionFacade {

    private final SessionService sessionService = new SessionImpl();
    private final TestContext testContext;

    public SessionFacade(TestContext testContext) {
        this.testContext = testContext;
    }

    public void requestToken() {
        Response response = sessionService.requestToken();
        System.out.println("Request Token Response: " + response.jsonPath().getString(Constants.REQUEST_TOKEN_PATH));
        testContext.set(Constants.REQUEST_TOKEN_CONTEXT, response.jsonPath().getString(Constants.REQUEST_TOKEN_PATH));
    }

    public void createSessionLogin() {
        sessionService.createSessionLogin(testContext.get(Constants.REQUEST_TOKEN_CONTEXT).toString());
    }

    public void createSession() {
        String requestToken = (String) testContext.get(Constants.REQUEST_TOKEN_CONTEXT);
        Response response = sessionService.createSession(requestToken);
        System.out.println("Session ID: " + response.jsonPath().getString(Constants.SESSION_ID_PATH));
        testContext.set(Constants.SESSION_ID_CONTEXT, response.jsonPath().getString(Constants.SESSION_ID_PATH));
    }
}
