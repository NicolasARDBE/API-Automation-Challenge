package steps;

import com.inter2025api.context.ScenarioContext;
import com.inter2025api.services.facedes.ListManagementFacade;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.bs.A;
import io.restassured.response.Response;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.inter2025api.utils.Constants;

public class CucumberHooks {

    private final ScenarioContext scenarioContext;
    private final ListManagementFacade listManagementFacade;
    
    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        this.listManagementFacade = new ListManagementFacade(scenarioContext.getTestContext());
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioContext.getTestContext().set("scenarioName", scenario.getName());

        try {
            listManagementFacade.requestToken();
            listManagementFacade.createSessionLogin();
            listManagementFacade.createSession();
        } catch (Exception e) {
            throw new RuntimeException("Movie API auth failed", e);
        }
    }

    @After("@list_management")
    public void afterListManagementScenario(Scenario scenario) {
        System.out.println("----------Ending list management scenario: " + scenario.getName());
        String listId = (String) scenarioContext.getTestContext().get("listId");
        String sessionId = (String) scenarioContext.getTestContext().get("sessionId");

        try {
            listManagementFacade.deleteList();
        } catch (Exception e) {
            System.err.println("Failed to delete list " + listId);
            e.printStackTrace();
        }
    }
}