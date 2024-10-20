package com.eneco.stepDefinitions;

import com.eneco.requests.CallService;
import com.eneco.utils.ContextStore;
import com.eneco.utils.GoRestApiClient;
import com.eneco.utils.LogUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Map;

public class UpdateUserStepDef extends GoRestApiClient {

    CallService callService = new CallService(requestSpecification);
    CallService callServiceWithInvalidToken = new CallService(requestSpecificationWithInvalidToken);

    @When("I request to update the user with the following details")
    public void iRequestToUpdateTheUserWithTheFollowingDetails(DataTable dataTable) {
        LogUtils.info("I request to update the user with the following details" + dataTable.asMaps(String.class, String.class).toString());
        Map<String, String> userDetails = dataTable.transpose().asMap(String.class, String.class);
        Response response = callService.executePutRequest(String.format("%s%s", USERS_ENDPOINT, ContextStore.get("createdUserId")), userDetails);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @When("I request to partially update the user with the following details")
    public void iRequestToPartiallyUpdateTheUserWithTheFollowingDetails(DataTable dataTable) {
        LogUtils.info("I request to partially update the user with the following details" + dataTable.asMaps(String.class, String.class).toString());
        Map<String, String> userDetails = dataTable.transpose().asMap(String.class, String.class);
        Response response = callService.executePatchRequest(String.format("%s%s", USERS_ENDPOINT, ContextStore.get("createdUserId")), userDetails);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @When("I request to update a user with invalid id {int} and the following details")
    public void iRequestToUpdateAUserWithInvalidIdAndTheFollowingDetails(int userId, DataTable dataTable) {
        LogUtils.info("I request to update a user with invalid id " + userId);
        Map<String, String> userDetails = dataTable.transpose().asMap(String.class, String.class);
        Response response = callService.executePutRequest(String.format("%s%s", USERS_ENDPOINT, userId), userDetails);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }

    @When("I request to update a user with invalid id {int} with no authentication")
    public void iRequestToUpdateAUserWithInvalidIdWithNoAuthentication(int userId, DataTable dataTable) {
        LogUtils.info("I request to update a user with invalid id " + userId + " with no authentication");
        Map<String, String> userDetails = dataTable.transpose().asMap(String.class, String.class);
        Response response = callServiceWithInvalidToken.executePutRequest(String.format("%s%s", USERS_ENDPOINT, userId), userDetails);

        LogUtils.info("Store the response for further assertions");
        ContextStore.put("response", response);
    }
}
