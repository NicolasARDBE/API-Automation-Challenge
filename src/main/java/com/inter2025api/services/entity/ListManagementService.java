package com.inter2025api.services.entity;

import com.inter2025api.models.Movie;
import com.inter2025api.models.MovieList;

import java.util.List;
import io.restassured.response.Response;

public interface ListManagementService {
    Response deleteList(String sessionId, String listId);
    Response createList(String sessionId, MovieList list);
    Response addItemsToList(String sessionId, String listId, List<Movie> movies);
    Response getListDetails(String sessionId, String listId);
    Response removeItemsFromList(String sessionId, String listId, List<Movie> movies);
    Response updateList(String sessionId, String listId, MovieList updatedList);
    Response updateListItems(String sessionId, String listId, List<Movie> movies);
}