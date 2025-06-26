package com.inter2025api.services.facedes;

import com.inter2025api.context.TestContext;
import com.inter2025api.services.entity.RatingManagementService;
import com.inter2025api.services.impl.RatingManagementImpl;
import com.inter2025api.utils.Constants;

import io.restassured.response.Response;

public class RatingManagementFacade {
    private final RatingManagementService ratingManagementService = new RatingManagementImpl();
    private final TestContext testContext;

    public RatingManagementFacade(TestContext testContext) {
        this.testContext = testContext;
    }

    public Response deleteRating(int movieId) {
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        return ratingManagementService.deleteRating(sessionId, movieId);
    }

    public Response addRating(int movieId, double rating) {
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        return ratingManagementService.addRating(sessionId, movieId, rating);
    }

    public Response getRatings() {
        String sessionId = (String) testContext.get(Constants.SESSION_ID_CONTEXT);
        return ratingManagementService.getRatings(sessionId);
    }
}
