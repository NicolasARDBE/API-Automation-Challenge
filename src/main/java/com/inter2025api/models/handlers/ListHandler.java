package com.inter2025api.models.handlers;

import java.util.List;

import com.inter2025api.models.Movie;
import com.inter2025api.models.MovieList;
import com.inter2025api.utils.FakerUtils;
import com.inter2025api.utils.JsonUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ListHandler {
    public MovieList createListParams(String listName, String description) {
        MovieList movieList = new MovieList();
        movieList.setName(listName);
        movieList.setDescription(description);
        return movieList;
    }

    public MovieList createListRandomParams() {
        MovieList movieList = new MovieList();
        movieList.setName(FakerUtils.generateRandomListName());
        movieList.setDescription(FakerUtils.generateRandomListDescription());
        return movieList;
    }

    public void addItemToList(MovieList list, Movie item) {
        if (list.getMovies() == null) {
            list.setMovies(List.of(item));
        } else {
            list.getMovies().add(item);
        }
    }

    public void addItemsToList(MovieList list, List<Movie> item) {
        if (list.getMovies() == null) {
            list.setMovies(item);
        } else {
            list.getMovies().addAll(item);
        }
    }

    public void addItemsToListFromJson(MovieList list, String jsonFilePath) {
        try{
            List<Movie> movies = JsonUtils.readJsonArray("src/test/resources/data/items.json",
             Movie.class);
            if (movies == null) {
                return;
            }
            addItemsToList(list, movies);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read items from JSON file: " + jsonFilePath, e);
        }
    }

        public List<Movie> extractItemsFromListResponse(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);
            JsonNode itemsNode = root.get("items");
            if (itemsNode == null || !itemsNode.isArray()) {
                return List.of();
            }
            return java.util.Arrays.asList(mapper.treeToValue(itemsNode, Movie[].class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract items from list response", e);
        }
    }

}