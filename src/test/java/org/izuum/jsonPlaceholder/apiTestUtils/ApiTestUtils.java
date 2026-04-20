package org.izuum.jsonPlaceholder.apiTestUtils;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

public abstract class ApiTestUtils {

    protected void checkStatusCode(String endpoint, int statusCode){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode);
    }

}
