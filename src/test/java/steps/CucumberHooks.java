package steps;

import com.inter2025api.context.ScenarioContext;

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

    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioContext.getTestContext().set("scenarioName", scenario.getName());

        try {
            String requestToken = getRequestToken();
            validateLogin(requestToken);
            String sessionId = createSession(requestToken);

            scenarioContext.getTestContext().set("requestToken", requestToken);
            scenarioContext.getTestContext().set("sessionId", sessionId);

        } catch (Exception e) {
            throw new RuntimeException("Movie API auth failed", e);
        }
    }

    @After("@list_management")
    public void afterListManagementScenario(Scenario scenario) {
        System.out.println("----------Ending list management scenario: " + scenario.getName());
        String listId = (String) scenarioContext.getTestContext().get("listId");
        String sessionId = (String) scenarioContext.getTestContext().get("sessionId");

        if (listId != null && sessionId != null) {
            try {
                deleteList(listId, sessionId);
            } catch (Exception e) {
                System.err.println("Failed to delete list " + listId);
                e.printStackTrace();
            }
        }
    }
        private String getRequestToken() {
        Response response = RestAssured.given()
            .queryParam("api_key", Constants.API_KEY)
        .when()
            .get("/authentication/token/new")
        .then()
            .statusCode(200)
            .extract().response();

        return response.jsonPath().getString("request_token");
    }

    private void validateLogin(String requestToken) {
        RestAssured.given()
            .queryParam("api_key", Constants.API_KEY)
            .header("Content-Type", "application/json")
            .body("{\"username\": \"" + Constants.USERNAME + "\", " +
                  "\"password\": \"" + Constants.PASSWORD + "\", " +
                  "\"request_token\": \"" + requestToken + "\"}")
        .when()
            .post("/authentication/token/validate_with_login")
        .then()
            .statusCode(200);
    }

    private String createSession(String requestToken) {
        Response response = RestAssured.given()
            .queryParam("api_key", Constants.API_KEY)
            .header("Content-Type", "application/json")
            .body("{\"request_token\": \"" + requestToken + "\"}")
        .when()
            .post("/authentication/session/new")
        .then()
            .statusCode(200)
            .extract().response();

        return response.jsonPath().getString("session_id");
    }

    private void deleteList(String listId, String sessionId) {
        RestAssured.given()
            .queryParam("api_key", Constants.API_KEY)
            .queryParam("session_id", sessionId)
        .when()
            .delete("/list/" + listId)
        .then()
            .statusCode(200);
    }
}