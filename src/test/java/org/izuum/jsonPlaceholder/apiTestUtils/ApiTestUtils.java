package org.izuum.jsonPlaceholder.apiTestUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

public class ApiTestUtils {

    public static void checkStatusCode(String endpoint, int statusCode){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode);
    }

    public static Response getResponse(String endpoint){
        return RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get(endpoint);
    }

}
