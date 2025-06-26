package steps;

import com.inter2025api.context.ScenarioContext;
import com.inter2025api.services.facedes.ListManagementFacade;
import com.inter2025api.services.facedes.RatingManagementFacade;
import com.inter2025api.services.facedes.SessionFacade;
import com.inter2025api.utils.Constants;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    private final ScenarioContext scenarioContext;
    private final ListManagementFacade listManagementFacade;
    private final SessionFacade sessionFacade;
    private final RatingManagementFacade ratingManagementFacade;
    
    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        this.listManagementFacade = new ListManagementFacade(scenarioContext.getTestContext());
        this.sessionFacade = new SessionFacade(scenarioContext.getTestContext());
        this.ratingManagementFacade = new RatingManagementFacade(scenarioContext.getTestContext());
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        try {
            sessionFacade.requestToken();
            sessionFacade.createSessionLogin();
            sessionFacade.createSession();
        } catch (Exception e) {
            throw new RuntimeException("Movie API auth failed", e);
        }
    }

    @After("@list_management")
    public void afterListManagementScenario(Scenario scenario) {
        System.out.println("----------Ending list management scenario: " + scenario.getName());
        String listId = (String) scenarioContext.getTestContext().get(Constants.LIST_ID_CONTEXT);
        try {
            listManagementFacade.deleteList();
        } catch (Exception e) {
            System.err.println("Failed to delete list " + listId);
            e.printStackTrace();
        }
    }
    @After("@add_rating")
    public void afterAddRatingScenario(Scenario scenario) {
        System.out.println("----------Ending add rating scenario: " + scenario.getName());
        int ratingId = (int) scenarioContext.getTestContext().get(Constants.MOVIE_ID_CONTEXT);
        try {
            ratingManagementFacade.deleteRating(ratingId);
        } catch (Exception e) {
            System.err.println("Failed to delete review " + ratingId);
            e.printStackTrace();
        }
    }
}