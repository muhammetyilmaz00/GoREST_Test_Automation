package com.eneco.stepDefinitions;

import com.eneco.pojo.User;
import com.eneco.requests.CallService;
import com.eneco.utils.GoRestApiClient;
import com.eneco.utils.ContextStore;
import com.eneco.utils.LogUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class CreateUserStepDef extends GoRestApiClient {

    CallService callService = new CallService(requestSpecification);

    @When("I request to create a new user with the following details")
    public void iRequestToCreateANewUserWithTheFollowingDetails(DataTable dataTable) {
        LogUtils.info("I request to create a new user with the following details" + dataTable.asMaps(String.class, String.class).toString());
        Map<String, String> userDetails = dataTable.transpose().asMap(String.class, String.class);
        Response response = callService.executePostRequest(USERS_ENDPOINT, userDetails);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);

        LogUtils.info("Store the user id for further assertions");
        User user = response.as(User.class);
        ContextStore.put("createdUserId", user.getId());
        ContextStore.put("createdUserName", user.getName());
    }

    @Then("the response must contain the user details")
    public void theResponseMustContainTheUserDetails(DataTable dataTable) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert the response contains the user details");
        User user = response.as(User.class);
        Map<String, String> expectedDetails = dataTable.transpose().asMap(String.class, String.class);

        Assert.assertEquals("User name does not match", expectedDetails.get("name"), user.getName());
        Assert.assertEquals("User gender does not match", expectedDetails.get("gender"), user.getGender());
        Assert.assertEquals("User email does not match", expectedDetails.get("email"), user.getEmail());
        Assert.assertEquals("User status does not match", expectedDetails.get("status"), user.getStatus());
    }


    @And("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert the response status code is " + expectedStatusCode);
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @When("I request to create a new user with the invalid details")
    public void iRequestToCreateANewUserWithTheInvalidDetails(DataTable dataTable) {
        LogUtils.info("I request to create a new user with the following details" + dataTable.asMaps(String.class, String.class).toString());
        Map<String, String> userDetails = dataTable.transpose().asMap(String.class, String.class);
        Response response = callService.executePostRequest(USERS_ENDPOINT, userDetails);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @And("the response must contain an error message {string} indicating field {string} is missing")
    public void theResponseMustContainAnErrorMessageIndicatingFieldIsMissing(String errorMessage, String field) {
        LogUtils.info("Acquire the response from the context store");
        Response response = ContextStore.get("response");

        LogUtils.info("Assert that the response shows that the " + field + " is missing");
        List<User> users = response.jsonPath().getList("", User.class);
        Assert.assertEquals(field, users.getFirst().getField());
        Assert.assertEquals(errorMessage, users.getFirst().getMessage());
    }
}

