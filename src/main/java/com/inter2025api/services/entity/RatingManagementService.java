package com.inter2025api.services.entity;

import io.restassured.response.Response;

public interface RatingManagementService {
    Response deleteRating(String sessionId, int movieId);
    Response addRating(String sessionId, int movieId, double rating);
    Response getRatings(String sessionId);   
}