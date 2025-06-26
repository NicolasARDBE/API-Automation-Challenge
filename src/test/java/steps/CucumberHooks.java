package steps;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.inter2025api.context.ScenarioContext;
import com.inter2025api.services.facedes.ListManagementFacade;
import com.inter2025api.services.facedes.RatingManagementFacade;
import com.inter2025api.services.facedes.SessionFacade;
import com.inter2025api.utils.Constants;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    private static final Logger logger = Logger.getLogger(CucumberHooks.class.getName());

    private final ScenarioContext scenarioContext;
    private final ListManagementFacade listManagementFacade;
    private final SessionFacade sessionFacade;
    private final RatingManagementFacade ratingManagementFacade;

    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext        = scenarioContext;
        this.listManagementFacade   = new ListManagementFacade(scenarioContext.getTestContext());
        this.sessionFacade          = new SessionFacade(scenarioContext.getTestContext());
        this.ratingManagementFacade = new RatingManagementFacade(scenarioContext.getTestContext());
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("▶️  Starting scenario: " + scenario.getName());
        try {
            logger.fine("Requesting request-token…");
            sessionFacade.requestToken();

            logger.fine("Creating session login…");
            sessionFacade.createSessionLogin();

            logger.fine("Creating session…");
            sessionFacade.createSession();

            logger.info("✅  Authentication and session set-up completed.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌  Movie API authentication failed.", e);
            throw new RuntimeException("Movie API auth failed", e);
        }
    }

    @After("@list_management")
    public void afterListManagementScenario(Scenario scenario) {
        logger.info("⏹️  Ending @list_management scenario: " + scenario.getName());
        String listId = (String) scenarioContext.getTestContext().get(Constants.LIST_ID_CONTEXT);
        logger.fine("Attempting to delete list: " + listId);
        try {
            listManagementFacade.deleteList();
            logger.info("🗑️  List " + listId + " deleted successfully.");
        } catch (Exception e) {
            logger.log(Level.WARNING, "⚠️  Failed to delete list " + listId, e);
        }
    }

    @After("@add_rating")
    public void afterAddRatingScenario(Scenario scenario) {
        logger.info("⏹️  Ending @add_rating scenario: " + scenario.getName());
        int movieId = (int) scenarioContext.getTestContext().get(Constants.MOVIE_ID_CONTEXT);
        logger.fine("Attempting to delete rating for movie: " + movieId);
        try {
            ratingManagementFacade.deleteRating(movieId);
            logger.info("🗑️  Rating for movie " + movieId + " deleted successfully.");
        } catch (Exception e) {
            logger.log(Level.WARNING, "⚠️  Failed to delete rating for movie " + movieId, e);
        }
    }
}