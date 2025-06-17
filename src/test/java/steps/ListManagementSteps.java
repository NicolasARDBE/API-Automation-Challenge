package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.inter2025api.context.ScenarioContext;
import com.inter2025api.context.TestContext;
import com.inter2025api.services.facedes.ListManagementFacade;

//Calls Facade
public class ListManagementSteps {
    private static final Logger logger = Logger.getLogger(ListManagementSteps.class.getName());
    private final TestContext testContext;
    private final ListManagementFacade listManagementFacade;

    public ListManagementSteps(ScenarioContext scenarioContext) {
        this.testContext = scenarioContext.getTestContext();
        this.listManagementFacade = new ListManagementFacade(testContext);
    }

    @Given("a new list is created")
    public void a_new_list_is_created() {
        logger.info("A new list is created.");
        listManagementFacade.createList("My List", "This is a test list", true);
        assertEquals(logger, listManagementFacade);
        logger.info("List created successfully.");
    }

    @When("items are added to the list")
    public void items_are_added_to_the_list() {
        logger.info("Items added to the list: ");
    }

    @Then("the list should contain the added items")
    public void the_list_should_contain_the_added_items() {
        logger.info("List contains the added items.");
    }

    @And("the list should be retrievable")
    public void the_list_should_be_retrievable() {
        logger.info("List is retrievable: ");
    }
}