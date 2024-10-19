package com.eneco.stepDefinitions;

import com.eneco.pojo.User;
import com.eneco.requests.CallService;
import com.eneco.utils.ContextStore;
import com.eneco.utils.GoRestApiClient;
import com.eneco.utils.LogUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GetUserStepDef extends GoRestApiClient {

    CallService callService = new CallService(requestSpecification);
    CallService callServiceWithInvalidToken = new CallService(requestSpecificationWithInvalidToken);

    @Then("the response should match the schema of a list of users")
    public void theResponseShouldMatchTheSchemaOfAListOfUsers() {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Validate the response against the schema");
        String schema;
        try {
            schema = new String(Files.readAllBytes(Paths.get("src/test/resources/config/schema.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    @Given("I request to get all users")
    public void iRequestToGetAllUsers() {
        LogUtils.info("Request to get all users");
        Response response = callService.executeGetRequest(USERS_ENDPOINT);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @And("I request to get the newly created user with {string}")
    public void iRequestToGetTheNewlyCreatedUserWith(String field) {
        LogUtils.info("Request to get the newly created user with "+field);
        Response response = null;
        if(field.equals("id")){
            response = callService.executeGetRequest(USERS_ENDPOINT + "/" + ContextStore.get("createdUserId"));
        } else if (field.equals("name")){
            response = callService.executeGetRequest(USERS_ENDPOINT + "?name=" + ContextStore.get("createdUserName"));
        }

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @When("I request to get a user with id {int}")
    public void iRequestToGetAUserWithId(int userId) {
        LogUtils.info("I request to get a user with id " + userId);
        Response response = callService.executeGetRequest(USERS_ENDPOINT + "/" + userId);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @And("the response must contain an error message indicating {string}")
    public void theResponseMustContainAnErrorMessageIndicating(String errorMessage) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert that the error message is " + errorMessage);
        User user = response.as(User.class);
        Assert.assertEquals(errorMessage, user.getMessage());
    }

    @When("I request to get all users on page {int} with {int} users per page")
    public void iRequestToGetAllUsersOnPageWithUsersPerPage(int page, int usersPerPage) {
        LogUtils.info("I request to get all users on page " + page + " with " + usersPerPage + " users per page");
        Response response = callService.executeGetRequest(USERS_ENDPOINT + "?page=" + page + "&per_page=" + usersPerPage);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @And("the response must contain exactly {int} users")
    public void theResponseMustContainExactlyUsers(int count) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert that the response contains exactly " + count + " users");
        List<User> users = response.as(List.class);
        Assert.assertEquals(count, users.size());
    }

    @And("the response headers must contain pagination information {int}")
    public void theResponseHeadersMustContainPaginationInformation(int limit) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert that the response contains pagination information");
        Assert.assertTrue(response.header("x-pagination-limit").contains(""+limit));
        Assert.assertTrue(response.header("x-pagination-page").contains("1"));
    }


    @When("I request to get a user with id {int} with no authentication")
    public void iRequestToGetAUserWithIdWithNoAuthentication(int userId) {
        LogUtils.info("I request to get a user with id " + userId + " with no authentication");
        Response response = callServiceWithInvalidToken.executeGetRequest(USERS_ENDPOINT + "/" + userId);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @When("I request to get a user with a non-numeric id {string}")
    public void iRequestToGetAUserWithANonNumericIdXyz(String userId) {
        LogUtils.info("I request to get a user with a non-numeric id " + userId);
        Response response = callService.executeGetRequest(USERS_ENDPOINT + "/" + userId);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }
}
