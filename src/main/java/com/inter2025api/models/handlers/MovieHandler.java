package com.inter2025api.models.handlers;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inter2025api.models.Movie;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Arrays;


public class MovieHandler {

public int generateRandomMovieId() {
    int[] reliableMovieIds = {
        278,      // The Shawshank Redemption
        238,      // The Godfather
        155,      // The Dark Knight
        680,      // Pulp Fiction
        550,      // Fight Club
        13,       // Forrest Gump
        27205,    // Inception
        603,      // The Matrix
        157336,   // Interstellar
        98        // Gladiator
    };

    int randomIndex = ThreadLocalRandom.current().nextInt(reliableMovieIds.length);
    return reliableMovieIds[randomIndex];
}

    public List<Movie> extractRatedMoviesFromResponse(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode resultsNode = root.get("results");

            if (resultsNode == null || !resultsNode.isArray()) {
                return List.of();
            }

            return Arrays.asList(mapper.treeToValue(resultsNode, Movie[].class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract rated movies from response", e);
        }
    }
}
