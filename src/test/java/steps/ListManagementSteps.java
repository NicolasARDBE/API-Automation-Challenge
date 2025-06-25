package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.inter2025api.context.ScenarioContext;
import com.inter2025api.context.TestContext;
import com.inter2025api.models.Movie;
import com.inter2025api.models.MovieList;
import com.inter2025api.models.handlers.ListHandler;
import com.inter2025api.services.facedes.ListManagementFacade;
import com.inter2025api.utils.Constants;

//Calls Facade
public class ListManagementSteps {
    private static final Logger logger = Logger.getLogger(ListManagementSteps.class.getName());
    private final TestContext testContext;
    private final ListManagementFacade listManagementFacade;
    private final ListHandler listHandler;

    public ListManagementSteps(ScenarioContext scenarioContext) {
        this.testContext = scenarioContext.getTestContext();
        this.listManagementFacade = new ListManagementFacade(testContext);
        this.listHandler = new ListHandler();
    }

    @Given("a new list is created")
    public void a_new_list_is_created() {
        logger.info("A new list is created.");
        MovieList movieList = listHandler.createListRandomParams();
        listManagementFacade.createList(movieList);
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        assertNotNull(listId, "List ID should not be null after creation.");
        logger.info("List created successfully.");
    }

    @When("items are added to the list")
    public void items_are_added_to_the_list() {
        MovieList movieList = (MovieList) testContext.get(Constants.LIST_CONTEXT);
        listHandler.addItemsToListFromJson(movieList, Constants.MOVIE_LIST_JSON);
        if (movieList.getMovies().isEmpty()) {
            assertTrue(false, "No items were added to the list.");
        }
        testContext.set(Constants.MOVIES_CONTEXT, movieList.getMovies());
        listManagementFacade.addItemsToList(movieList);
        logger.info("Items added to the list: " + movieList.getMovies());
    }

    @When("the items are deleted from the list")
    public void the_items_are_deleted_from_the_list() {
        List<Movie> items = (List<Movie>) testContext.get(Constants.MOVIES_CONTEXT);
        listManagementFacade.removeItemsFromList(items);
        logger.info("Items deleted from the list.");
    }

    @When("the list is deleted")
    public void the_list_is_deleted() {
        listManagementFacade.deleteList();
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        assertTrue(listId.isEmpty(), "List ID should be empty after deletion.");
        logger.info("List deleted successfully.");
    }

    @Then("the list should contain the added items")
    public void the_list_should_contain_the_added_items() {
        var response = listManagementFacade.getListDetails();
        List<Movie> itemsFromResponse = listHandler.extractItemsFromListResponse(response.asString());
        List<Movie> itemsFromRequest = (List<Movie>) testContext.get(Constants.MOVIES_CONTEXT);
        assertEquals(itemsFromRequest.size(), itemsFromResponse.size(), "The number of items in the list does not match the expected count.");
        logger.info("List contains the added items: " + itemsFromResponse);
    }

    @Then("the list should reflect the items deletions")
    public void the_list_should_reflect_the_items_deletions() {
        var response = listManagementFacade.getListDetails();
        List<Movie> itemsFromResponse = listHandler.extractItemsFromListResponse(response.asString());
        assertTrue(itemsFromResponse.isEmpty(), "The list should be empty after item deletions.");
        logger.info("List is empty after deletions: " + itemsFromResponse);
    }

    @Then("retrieving the list should confirm it no longer exists")
    public void retrieving_the_list_should_confirm_it_no_longer_exists() {
        var response = listManagementFacade.getListDetails();
        assertEquals(404, response.getStatusCode(), "The list should not exist after deletion.");
        logger.info("List retrieval confirmed it no longer exists. Status code: " + response.getStatusCode());
    }
    
}