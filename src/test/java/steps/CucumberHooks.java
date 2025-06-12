package steps;

import com.api_testing.context.ScenarioContext;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    private final ScenarioContext scenarioContext;

    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioContext.getTestContext().set("scenarioName", scenario.getName());
    }

    @Before("@movie")
    public void beforeMovieScenario(Scenario scenario) {
        System.out.println("----------Starting movie scenario: " + scenario.getName());
        
    }
}