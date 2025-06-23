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

    public Response deleteList() {
        String sessionId = (String) testContext.get("sessionId");
        String listId = (String) testContext.get("listId");
        return listManagementService.deleteList(sessionId, listId);
    }

    public Response createList(MovieList list) {
        String sessionId = (String) testContext.get("sessionId");
        Response response = listManagementService.createList(sessionId, list);
        testContext.set("listId", response.jsonPath().getString("list_id"));
        testContext.set("list", list);
        return response;
    }

    public Response addItemsToList(MovieList list) {
        String sessionId = (String) testContext.get("sessionId");
        String listId = (String) testContext.get("listId");
        return listManagementService.addItemsToList(sessionId, listId, list.getMovies());
    }

    public Response getListDetails() {
        String sessionId = (String) testContext.get("sessionId");
        String listId = (String) testContext.get("listId");
        return listManagementService.getListDetails(sessionId, listId);
    }
}