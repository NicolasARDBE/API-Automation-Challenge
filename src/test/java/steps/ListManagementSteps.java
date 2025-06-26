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
import com.inter2025api.utils.FakerUtils;

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
        logger.info("Creating a new movie list...");
        MovieList movieList = listHandler.createListRandomParams();
        logger.fine("Generated list: " + movieList);
        listManagementFacade.createList(movieList);
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        logger.info("Retrieved list ID from context: " + listId);
        assertNotNull(listId, "List ID should not be null after creation.");
        logger.info("List created successfully.");
    }

    @When("items are added to the list")
    public void items_are_added_to_the_list() {
        logger.info("Adding items to the list...");
        MovieList movieList = (MovieList) testContext.get(Constants.LIST_CONTEXT);
        logger.fine("Retrieved list from context: " + movieList);
        listHandler.addItemsToListFromJson(movieList, Constants.MOVIE_LIST_JSON);
        if (movieList.getMovies().isEmpty()) {
            logger.warning("No items were added to the list.");
            assertTrue(false, "No items were added to the list.");
        }
        testContext.set(Constants.MOVIES_CONTEXT, movieList.getMovies());
        listManagementFacade.addItemsToList(movieList);
        logger.info("Items successfully added to the list: " + movieList.getMovies());
    }

    @When("the items are deleted from the list")
    public void the_items_are_deleted_from_the_list() {
        logger.info("Deleting items from the list...");
        List<Movie> items = (List<Movie>) testContext.get(Constants.MOVIES_CONTEXT);
        logger.fine("Items to delete: " + items);
        listManagementFacade.removeItemsFromList(items);
        logger.info("Items deleted from the list.");
    }

    @When("the list is deleted")
    public void the_list_is_deleted() {
        logger.info("Deleting the list...");
        listManagementFacade.deleteList();
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        logger.info("List ID after deletion: " + listId);
        assertTrue(listId.isEmpty(), "List ID should be empty after deletion.");
        logger.info("List deleted successfully.");
    }

    @When("the list information is updated")
    public void the_list_information_is_updated() {
        logger.info("Updating list information...");
        MovieList movieList = listHandler.createListRandomParams();
        logger.fine("Updated list parameters: " + movieList);
        testContext.set(Constants.UPDATED_LIST_CONTEXT, movieList);
        listManagementFacade.updateList(movieList);
        String listId = (String) testContext.get(Constants.LIST_ID_CONTEXT);
        logger.info("List ID after update: " + listId);
        assertNotNull(listId, "List ID should not be null after update.");
        logger.info("List information updated successfully.");
    }

    @When("the items in the list are updated")
    public void the_items_in_the_list_are_updated() {
        logger.info("Updating items in the list...");
        List<Movie> items = (List<Movie>) testContext.get(Constants.MOVIES_CONTEXT);
        items.forEach(item -> item.setComment("Updated comment for " + FakerUtils.generateRandomComment()));
        testContext.set(Constants.MOVIES_CONTEXT, items);
        listManagementFacade.updateListItems(items);
        logger.info("Items in the list updated: " + items);
    }

    @Then("the list should contain the added items")
    public void the_list_should_contain_the_added_items() {
        logger.info("Verifying added items are present in the list...");
        var response = listManagementFacade.getListDetails();
        List<Movie> itemsFromResponse = listHandler.extractItemsFromListResponse(response.asString());
        List<Movie> itemsFromRequest = (List<Movie>) testContext.get(Constants.MOVIES_CONTEXT);
        logger.fine("Items from response: " + itemsFromResponse);
        logger.fine("Items from request: " + itemsFromRequest);
        assertEquals(itemsFromRequest.size(), itemsFromResponse.size(), "The number of items in the list does not match the expected count.");
        logger.info("List contains the added items successfully.");
    }

    @Then("the list should reflect the items deletions")
    public void the_list_should_reflect_the_items_deletions() {
        logger.info("Verifying list reflects item deletions...");
        var response = listManagementFacade.getListDetails();
        List<Movie> itemsFromResponse = listHandler.extractItemsFromListResponse(response.asString());
        logger.fine("Remaining items in list: " + itemsFromResponse);
        assertTrue(itemsFromResponse.isEmpty(), "The list should be empty after item deletions.");
        logger.info("List is empty after deletions.");
    }

    @Then("retrieving the list should confirm it no longer exists")
    public void retrieving_the_list_should_confirm_it_no_longer_exists() {
        logger.info("Confirming list no longer exists...");
        var response = listManagementFacade.getListDetails();
        int statusCode = response.getStatusCode();
        logger.info("List retrieval status code: " + statusCode);
        assertEquals(404, statusCode, "The list should not exist after deletion.");
        logger.info("List retrieval confirmed it no longer exists.");
    }

    @Then("the list should reflect the updated information")
    public void the_list_should_reflect_the_updated_information() {
        logger.info("Verifying list reflects updated information...");
        var response = listManagementFacade.getListDetails();
        MovieList updatedList = listHandler.extractMovieListFromResponse(response.asString());
        MovieList originalList = (MovieList) testContext.get(Constants.UPDATED_LIST_CONTEXT);
        logger.fine("Updated list from response: " + updatedList);
        logger.fine("Expected updated list: " + originalList);
        assertEquals(originalList.getName(), updatedList.getName(), "The list name should match the updated name.");
        assertEquals(originalList.getDescription(), updatedList.getDescription(), "The list description should match the updated description.");
        logger.info("List information updated correctly.");
    }

    @Then("the list should reflect the updated items")
    public void the_list_should_reflect_the_updated_items() {
        logger.info("Verifying list reflects updated items...");
        var response = listManagementFacade.getListDetails();
        List<Movie> itemsFromResponse = listHandler.extractItemsFromListResponse(response.asString());
        List<Movie> itemsFromRequest = (List<Movie>) testContext.get(Constants.MOVIES_CONTEXT);
        logger.fine("Updated items from request: " + itemsFromRequest);
        logger.fine("Updated items from response: " + itemsFromResponse);
        assertEquals(itemsFromRequest.size(), itemsFromResponse.size(), "The number of items in the list does not match the expected count after update.");
        for (int i = 0; i < itemsFromRequest.size(); i++) {
            assertEquals(itemsFromRequest.get(i).getComment(), itemsFromResponse.get(i).getComment(), "The item comment should match the updated comment.");
        }
        logger.info("List items updated correctly.");
    }
}
