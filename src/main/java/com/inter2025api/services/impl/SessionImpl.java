package com.inter2025api.services.impl;

import com.inter2025api.config.RestAssuredClient;
import com.inter2025api.config.RestAssuredClientFactory;
import com.inter2025api.models.dtos.SessionRequest;
import com.inter2025api.services.entity.SessionService;
import com.inter2025api.utils.ConfigUtil;
import com.inter2025api.utils.Constants;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SessionImpl implements SessionService{

    private final RestAssuredClient restAssuredClient = new RestAssuredClient();

    @Override
    public Response requestToken() {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        return restAssuredClient.get(request, ConfigUtil.getProperty(Constants.REQUEST_TOKEN));
    }

    @Override
    public Response createSessionLogin(String requestToken) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        SessionRequest sessionRequest = new SessionRequest();
        sessionRequest.setUsername(Constants.USERNAME);
        sessionRequest.setPassword(Constants.PASSWORD);
        sessionRequest.setRequestToken(requestToken);
        restAssuredClient.addBody(request, sessionRequest);
        return restAssuredClient.post(request, ConfigUtil.getProperty(Constants.CREATE_SESSION_LOGIN));
    }

    @Override
    public Response createSession(String requestToken) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        java.util.Map<String, String> credentials = new java.util.HashMap<>();
        credentials.put(Constants.REQUEST_TOKEN_PATH, requestToken);
        restAssuredClient.addBody(request, credentials);
        return restAssuredClient.post(request, ConfigUtil.getProperty(Constants.CREATE_SESSION));
    }
    
}
