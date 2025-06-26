package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.logging.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.inter2025api.context.ScenarioContext;
import com.inter2025api.context.TestContext;
import com.inter2025api.models.Movie;
import com.inter2025api.models.handlers.MovieHandler;
import com.inter2025api.services.facedes.RatingManagementFacade;
import com.inter2025api.utils.Constants;
import com.inter2025api.utils.FakerUtils;

public class RatingManagementSteps {

    private static final Logger logger = Logger.getLogger(ListManagementSteps.class.getName());
    private final TestContext testContext;
    private final MovieHandler movieHandler;
    private final RatingManagementFacade ratingManagementFacade;
    
    public RatingManagementSteps(ScenarioContext scenarioContext) {
        this.testContext = scenarioContext.getTestContext();
        this.ratingManagementFacade = new RatingManagementFacade(testContext);
        this.movieHandler = new MovieHandler();
    }

    @Given("a movie is available to rate")
    public void a_movie_is_available_to_rate() {
        logger.info("A movie is available to rate.");
        int movieId = movieHandler.generateRandomMovieId();
        testContext.set(Constants.MOVIE_ID_CONTEXT, movieId);
        logger.info("Movie available for rating: " +  movieId);
    }

    @When("a rating is added to the movie")
    public void a_rating_is_added_to_the_movie() throws InterruptedException {
        int movieId = (int) testContext.get(Constants.MOVIE_ID_CONTEXT);
        double rating = FakerUtils.generateRandomRating();
        logger.info("Adding rating " + rating + " to movie ID: " + movieId);
        ratingManagementFacade.addRating(movieId, rating);
        testContext.set(Constants.RATING_CONTEXT, rating);
        logger.info("Rating added successfully.");
        Thread.sleep(2000);
    }

    @Then("the movie should appear in the rated movies list")
    public void the_movie_should_appear_in_the_rated_movies_list() {
        int movieId = (int) testContext.get(Constants.MOVIE_ID_CONTEXT);
        double expectedRating = (double) testContext.get(Constants.RATING_CONTEXT);
        logger.info("Verifying rated movie ID: " + movieId + " with rating: " + expectedRating);
        var response = ratingManagementFacade.getRatings();
        assertEquals(200, response.getStatusCode(), "Failed to fetch rated movies");
        List<Movie> ratedMovies = movieHandler.extractRatedMoviesFromResponse(response.asString());
        // Buscar la película por ID
        Movie ratedMovie = ratedMovies.stream()
            .filter(m -> m.getId() == movieId)
            .findFirst()
            .orElse(null);

        assertNotNull(ratedMovie, "Rated movie not found in user's rated list");
        assertEquals(expectedRating, ratedMovie.getRating(), 0.01, "Rating does not match the expected value");

        logger.info("Movie verified successfully in rated list: " + ratedMovie.getId() + " with rating: " + ratedMovie.getRating());
    }
}