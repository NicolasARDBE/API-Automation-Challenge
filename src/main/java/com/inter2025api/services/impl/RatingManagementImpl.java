package com.inter2025api.services.impl;

import java.util.Map;

import com.inter2025api.config.RestAssuredClient;
import com.inter2025api.config.RestAssuredClientFactory;
import com.inter2025api.services.entity.RatingManagementService;
import com.inter2025api.utils.ConfigUtil;
import com.inter2025api.utils.Constants;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RatingManagementImpl implements RatingManagementService{

    private final RestAssuredClient restAssuredClient = new RestAssuredClient();

    @Override
    public Response deleteRating(String sessionId, int movieId) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        request.pathParam(Constants.MOVIE_ID_PATH, movieId);
        return restAssuredClient.delete(request, ConfigUtil.getProperty(Constants.DELETE_RATING));
    }

    @Override
    public Response addRating(String sessionId, int movieId, double rating) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        request.pathParam(Constants.MOVIE_ID_PATH, movieId);
        restAssuredClient.addBody(request, Map.of("value", rating));
        return restAssuredClient.post(request, ConfigUtil.getProperty(Constants.ADD_RATING));
    }

    @Override
    public Response getRatings(String sessionId) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        request.pathParam(Constants.ACCOUNT_ID_PARAM ,Constants.ACCOUNT_ID);
        return restAssuredClient.get(request, ConfigUtil.getProperty(Constants.GET_RATED_MOVIES));
    }
}