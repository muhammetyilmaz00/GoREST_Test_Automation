package com.eneco.requests;

import com.eneco.utils.PropertiesFactory;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CallService {

    private final RequestSpecification requestSpecification;

    public CallService(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    /**
     * This method performs a GET request to a specified endpoint.
     * The boolean flags logRequests and logResponses control whether these logs are generated or not.
     * The method returns the response from the GET request for further processing
     */
    public Response executeGetRequest(String endpoint) {
        RequestSpecification requestSpec = given(requestSpecification);

        if (PropertiesFactory.getPropertyFromApplication("logRequests").equals("true"))
            requestSpec.log().all();

        Response response = requestSpec
                .when()
                .get(endpoint);

        if (PropertiesFactory.getPropertyFromApplication("logResponses").equals("true"))
            response.then().log().body();

        return response;
    }

    /**
     * This method is for making a POST request to a specified endpoint.
     * The boolean flags logRequests and logResponses control whether these logs are generated or not.
     * The method returns the response from the POST request for further processing.
     */
    public Response executePostRequest(String endpoint, Map<String, String> queryParams) {
        RequestSpecification requestSpec = given(requestSpecification);

        if (PropertiesFactory.getPropertyFromApplication("logRequests").equals("true"))
            requestSpec.log().all();

        Response response = requestSpec
                .body(queryParams)
                .when()
                .post(endpoint);

        if (PropertiesFactory.getPropertyFromApplication("logResponses").equals("true"))
            response.then().log().body();

        return response;
    }


    /**
     * This method is for making a PUT request to a specified endpoint.
     * The boolean flags logRequests and logResponses control whether these logs are generated or not.
     * The method returns the response from the PUT request for further processing.
     */
    public Response executePutRequest(String endpoint, Map<String, String> queryParams) {
        RequestSpecification requestSpec = given(requestSpecification);

        if (PropertiesFactory.getPropertyFromApplication("logRequests").equals("true"))
            requestSpec.log().uri().log().body();

        Response response = requestSpec
                .body(queryParams)
                .when()
                .put(endpoint);

        if (PropertiesFactory.getPropertyFromApplication("logResponses").equals("true"))
            response.then().log().body();

        return response;
    }


    /**
     * This method is for making a PATCH request to a specified endpoint.
     * The boolean flags logRequests and logResponses control whether these logs are generated or not.
     * The method returns the response from the PATCH request for further processing.
     */
    public Response executePatchRequest(String endpoint, Map<String, String> queryParams) {
        RequestSpecification requestSpec = given(requestSpecification);

        if (PropertiesFactory.getPropertyFromApplication("logRequests").equals("true"))
            requestSpec.log().uri().log().body();

        Response response = requestSpec
                .body(queryParams)
                .when()
                .patch(endpoint);

        if (PropertiesFactory.getPropertyFromApplication("logResponses").equals("true"))
            response.then().log().body();

        return response;
    }

    /**
     * This method is used to send a DELETE request to a specified endpoint, which includes an userId as a path parameter.
     * The boolean flags logRequests and logResponses control whether these logs are generated or not.
     * It returns the response from the DELETE request, allowing further processing if needed.
     */
    public Response executeDeleteRequest(String endpoint, int userId) {
        RequestSpecification requestSpec = given(requestSpecification);

        if (PropertiesFactory.getPropertyFromApplication("logRequests").equals("true"))
            requestSpec.log().uri().log().body();

        Response response = requestSpec
                .when()
                .delete(endpoint + userId);

        if (PropertiesFactory.getPropertyFromApplication("logResponses").equals("true"))
            response.then().log().body();
        return response;
    }

}
