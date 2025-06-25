package com.inter2025api.models.handlers;

import java.util.ArrayList;
import java.util.List;

import com.inter2025api.models.Movie;
import com.inter2025api.models.MovieList;
import com.inter2025api.utils.Constants;
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
            List<Movie> movies = JsonUtils.readJsonArray(Constants.MOVIE_LIST_JSON,
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
            JsonNode resultsNode = root.get("results");
            JsonNode commentsNode = root.get("comments");
            if (resultsNode == null || !resultsNode.isArray()) {
                return List.of();
            }
            List<Movie> movies = new ArrayList<>();
            for (JsonNode movieNode : resultsNode) {
                Movie movie = mapper.treeToValue(movieNode, Movie.class);
                // Buscar comentario por "movie:{id}"
                if (commentsNode != null && movie.getId() != 0) {
                    String commentKey = "movie:" + movie.getId();
                    JsonNode commentValue = commentsNode.get(commentKey);
                    if (commentValue != null && !commentValue.isNull()) {
                        movie.setComment(commentValue.asText());
                    }
                }
                movies.add(movie);
            }
            return movies;
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract items from list response", e);
        }
    }


    public MovieList extractMovieListFromResponse(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse, MovieList.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract MovieList from response", e);
        }
    }
}