package com.inter2025api.services.entity;

import com.inter2025api.models.MovieList;

import io.restassured.response.Response;

public interface ListManagementService {
    Response requestToken();
    Response createSessionLogin(String requestToken);
    Response createSession(String requestToken);
    Response deleteList(String sessionId, String listId);
    Response createList(String sessionId, MovieList list);

    Response addItemToList(String sessionId, String listId, String mediaId);
    Response removeItemFromList(String sessionId, String listId, String mediaId);
    Response getListDetails(String sessionId, String listId);
    Response getListItems(String sessionId, String listId);

    

    Response updateList(String sessionId, String listId, String name, String description, boolean isPublic);
}