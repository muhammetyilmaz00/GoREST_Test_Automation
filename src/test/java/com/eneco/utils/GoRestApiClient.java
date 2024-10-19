package com.eneco.utils;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GoRestApiClient {
    public static RequestSpecification requestSpecification;
    public static RequestSpecification requestSpecificationWithInvalidToken;
    public static final String USERS_ENDPOINT = "users";

    /**
     * Set up the base URI and request specification for API calls
     */
    public static void setUp() {
        baseURI = PropertiesFactory.getPropertyFromApplication("baseURI");
        requestSpecification = given()
                .headers("Authorization", "Bearer " + PropertiesFactory.getPropertyFromConfiguration("token"))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
        requestSpecificationWithInvalidToken = given().accept(ContentType.JSON).header("Authorization", "Bearer 123");
    }
}