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

    @Then("the list should contain the added items")
    public void the_list_should_contain_the_added_items() {
        var response = listManagementFacade.getListDetails();
        List<Movie> itemsFromResponse = listHandler.extractItemsFromListResponse(response.asString());
        List<Movie> itemsFromRequest = (List<Movie>) testContext.get(Constants.MOVIES_CONTEXT);
        assertEquals(itemsFromRequest.size(), itemsFromResponse.size(), "The number of items in the list does not match the expected count.");
        logger.info("List contains the added items: " + itemsFromResponse);
    }
}