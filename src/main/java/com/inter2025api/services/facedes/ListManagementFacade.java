package com.inter2025api.services.facedes;

import java.util.List;

import com.inter2025api.context.TestContext;
import com.inter2025api.models.Movie;
import com.inter2025api.models.MovieList;
import com.inter2025api.services.impl.ListManagementImpl;
import com.inter2025api.utils.Constants;
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
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        Response response = listManagementService.deleteList(sessionId, listId);
        testContext.set(Constants.LIST_ID_CONTEXT, "");
        testContext.set(Constants.LIST_CONTEXT, null);
        return response;
    }

    public Response createList(MovieList list) {
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        Response response = listManagementService.createList(sessionId, list);
        testContext.set(Constants.LIST_ID_CONTEXT, response.jsonPath().getString(Constants.LIST_ID_PATH));
        testContext.set(Constants.LIST_CONTEXT, list);
        return response;
    }

    public Response addItemsToList(MovieList list) {
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        return listManagementService.addItemsToList(sessionId, listId, list.getMovies());
    }

    public Response getListDetails() {
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        return listManagementService.getListDetails(sessionId, listId);
    }

    public Response removeItemsFromList(List<Movie> movies) {
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        return listManagementService.removeItemsFromList(sessionId, listId, movies);
    }

    public Response updateList(MovieList updatedList) {
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        return listManagementService.updateList(sessionId, listId, updatedList);
    }

    public Response updateListItems(List<Movie> movies) {
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        return listManagementService.updateListItems(sessionId, listId, movies);
    }
}