package com.inter2025api.services.facedes;

import com.inter2025api.context.TestContext;
import com.inter2025api.models.MovieList;
import com.inter2025api.services.impl.ListManagementImpl;
import com.inter2025api.services.entity.ListManagementService;

import io.restassured.response.Response;

//Uses ListrManagementService to handle list management operations through the implementation class ListManagementImpl.
public class ListManagementFacade {
    private final ListManagementService listManagementService = new ListManagementImpl();
    private final TestContext testContext;

    public ListManagementFacade(TestContext testContext) {
        this.testContext = testContext;
    }

    public void requestToken() {
        Response response = listManagementService.requestToken();
        System.out.println("Request Token Response: " + response.jsonPath().getString("request_token"));
        testContext.set("request_token", response.jsonPath().getString("request_token"));
    }

    public void createSessionLogin() {
        listManagementService.createSessionLogin(testContext.get("request_token").toString());
    }

    public void createSession() {
        String requestToken = (String) testContext.get("request_token");
        Response response = listManagementService.createSession(requestToken);
        System.out.println("Session ID: " + response.jsonPath().getString("session_id"));
        testContext.set("sessionId", response.jsonPath().getString("session_id"));
    }

    public Response deleteList() {
        String sessionId = (String) testContext.get("sessionId");
        String listId = (String) testContext.get("listId");
        return listManagementService.deleteList(sessionId, listId);
    }


    public Response createList(MovieList list) {
        String sessionId = (String) testContext.get("sessionId");
        Response response = listManagementService.createList(sessionId, list);
        System.out.println("Created List ID: " + response.jsonPath().getString("list_id"));
        testContext.set("listId", response.jsonPath().getString("list_id"));
        return response;
    }
}