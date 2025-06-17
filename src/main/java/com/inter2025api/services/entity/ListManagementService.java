package com.inter2025api.services.entity;

import io.restassured.response.Response;

public interface ListManagementService {
    Response createList(String sessionId, String name, String description, boolean isPublic);
    Response addItemToList(String sessionId, String listId, String mediaId);
    Response removeItemFromList(String sessionId, String listId, String mediaId);
    Response getListDetails(String sessionId, String listId);
    Response getListItems(String sessionId, String listId);
    Response deleteList(String sessionId, String listId);
    Response updateList(String sessionId, String listId, String name, String description, boolean isPublic);
}