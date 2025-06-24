package steps;

import com.inter2025api.context.ScenarioContext;
import com.inter2025api.services.facedes.ListManagementFacade;
import com.inter2025api.services.facedes.SessionFacade;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    private final ScenarioContext scenarioContext;
    private final ListManagementFacade listManagementFacade;
    private final SessionFacade sessionFacade;
    
    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        this.listManagementFacade = new ListManagementFacade(scenarioContext.getTestContext());
        this.sessionFacade = new SessionFacade(scenarioContext.getTestContext());
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        //scenarioContext.getTestContext().set("scenarioName", scenario.getName());
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
        String listId = (String) scenarioContext.getTestContext().get("listId");
        try {
            listManagementFacade.deleteList();
        } catch (Exception e) {
            System.err.println("Failed to delete list " + listId);
            e.printStackTrace();
        }
    }
}