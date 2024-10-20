package com.eneco.stepDefinitions;

import com.eneco.pojo.User;
import com.eneco.requests.CallService;
import com.eneco.utils.ContextStore;
import com.eneco.utils.GoRestApiClient;
import com.eneco.utils.LogUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

public class DeleteUserStepDef extends GoRestApiClient {
    CallService callService = new CallService(requestSpecification);
    CallService callServiceWithInvalidToken = new CallService(requestSpecificationWithInvalidToken);

    @Given("I request to delete the user")
    public void deleteUser() {
        LogUtils.info("I request to delete the user");
        Response response = callService.executeDeleteRequest(USERS_ENDPOINT, ContextStore.get("createdUserId"));

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @And("the response must have no body content")
    public void theResponseMustHaveNoBodyContent() {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert that the delete response is empty");
        Assert.assertTrue(response.asString().isEmpty());
    }

    @When("I request to delete the user with id {int}")
    public void iRequestToDeleteTheUserWithId(int userId) {
        LogUtils.info("I request to delete the user with id " + userId);
        Response response = callService.executeDeleteRequest(USERS_ENDPOINT, userId);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @And("the response must contain the error message {string}")
    public void theResponseMustContainTheErrorMessage(String message) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert that the error message is " + message);
        User user = response.as(User.class);
        Assert.assertEquals(message, user.getMessage());
    }

    @When("I request to delete the user without authentication")
    public void iRequestToDeleteTheUserWithoutAuthentication() {
        LogUtils.info("I request to delete the user without authentication");
        Response response = callServiceWithInvalidToken.executeDeleteRequest(USERS_ENDPOINT, ContextStore.get("createdUserId"));

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }
}
