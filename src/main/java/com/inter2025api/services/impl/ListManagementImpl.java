package com.inter2025api.services.impl;

import java.util.List;

import com.inter2025api.config.RestAssuredClient;
import com.inter2025api.config.RestAssuredClientFactory;
import com.inter2025api.services.entity.ListManagementService;
import com.inter2025api.utils.ConfigUtil;
import com.inter2025api.utils.Constants;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.inter2025api.models.Movie;
import com.inter2025api.models.MovieList;
import com.inter2025api.models.dtos.AddMoviesRequest;

public class ListManagementImpl implements ListManagementService{

    private final RestAssuredClient restAssuredClient = new RestAssuredClient();

    @Override
    public Response deleteList(String sessionId, String listId) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, "api_key", Constants.API_KEY);
        restAssuredClient.addQueryParam(request, "session_id", sessionId);
        request.pathParam("list-id", listId);
        return restAssuredClient.delete(request, ConfigUtil.getProperty("list.delete"));
    }

    @Override
    public Response createList(String sessionId, MovieList list) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, "api_key", Constants.API_KEY);
        restAssuredClient.addQueryParam(request, "session_id", sessionId);
        restAssuredClient.addBody(request, list);
        return restAssuredClient.post(request, ConfigUtil.getProperty("list.create"));
    }

    @Override
    public Response addItemsToList(String sessionId, String listId, List<Movie> movies) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, "api_key", Constants.API_KEY);
        restAssuredClient.addQueryParam(request, "session_id", sessionId);
        request.pathParam("list-id", listId);
        AddMoviesRequest body = new AddMoviesRequest(movies);
        restAssuredClient.addBody(request, body);
        return restAssuredClient.post(request, ConfigUtil.getProperty("list.addItems"));
    }

    @Override
    public Response getListDetails(String sessionId, String listId) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, "api_key", Constants.API_KEY);
        restAssuredClient.addQueryParam(request, "session_id", sessionId);
        request.pathParam("list-id", listId);
        return restAssuredClient.get(request, ConfigUtil.getProperty("list.get"));
    }

    @Override
    public Response removeItemFromList(String sessionId, String listId, String mediaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeItemFromList'");
    }

    @Override
    public Response updateList(String sessionId, String listId, String name, String description, boolean isPublic) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateList'");
    }   
}