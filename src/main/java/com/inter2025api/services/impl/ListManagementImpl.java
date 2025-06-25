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
import com.inter2025api.models.dtos.AddRemoveMoviesRequest;

public class ListManagementImpl implements ListManagementService{

    private final RestAssuredClient restAssuredClient = new RestAssuredClient();

    @Override
    public Response deleteList(String sessionId, String listId) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        request.pathParam(Constants.LIST_ID_PATH_PARAM, listId);
        return restAssuredClient.delete(request, ConfigUtil.getProperty(Constants.DELETE_LIST));
    }

    @Override
    public Response createList(String sessionId, MovieList list) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        restAssuredClient.addBody(request, list);
        return restAssuredClient.post(request, ConfigUtil.getProperty(Constants.CREATE_LIST));
    }

    @Override
    public Response addItemsToList(String sessionId, String listId, List<Movie> movies) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        request.pathParam(Constants.LIST_ID_PATH_PARAM, listId);
        AddRemoveMoviesRequest body = new AddRemoveMoviesRequest(movies);
        restAssuredClient.addBody(request, body);
        return restAssuredClient.post(request, ConfigUtil.getProperty(Constants.ADD_ITEMS));
    }

    @Override
    public Response getListDetails(String sessionId, String listId) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        request.pathParam(Constants.LIST_ID_PATH_PARAM, listId);
        return restAssuredClient.get(request, ConfigUtil.getProperty(Constants.GET));
    }

    @Override
    public Response removeItemsFromList(String sessionId, String listId, List<Movie> movies) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        request.pathParam(Constants.LIST_ID_PATH_PARAM, listId);
        AddRemoveMoviesRequest body = new AddRemoveMoviesRequest(movies);
        restAssuredClient.addBody(request, body);
        return restAssuredClient.delete(request, ConfigUtil.getProperty(Constants.REMOVE_ITEMS));
    }

    @Override
    public Response updateList(String sessionId, String listId,  MovieList updatedList) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        request.pathParam(Constants.LIST_ID_PATH_PARAM, listId);
        restAssuredClient.addBody(request, updatedList);
        return restAssuredClient.put(request, ConfigUtil.getProperty(Constants.UPDATE_LIST));
    }

    @Override
    public Response updateListItems(String sessionId, String listId, List<Movie> movies) {
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, Constants.API_KEY_PARAM, Constants.API_KEY);
        restAssuredClient.addQueryParam(request, Constants.SESSION_ID_PARAM, sessionId);
        request.pathParam(Constants.LIST_ID_PATH_PARAM, listId);
        AddRemoveMoviesRequest body = new AddRemoveMoviesRequest(movies);
        restAssuredClient.addBody(request, body);
        return restAssuredClient.put(request, ConfigUtil.getProperty(Constants.UPDATE_LIST_ITEMS));
    }   
}