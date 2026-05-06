package org.izuum.jsonPlaceholder.apiTestUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    public static boolean checkSetsOfId(String firstEndpoint, String secondEndpoint, String comparableFields){
        Response nestedResponse = getResponse(firstEndpoint);
        Response filteredResponse = getResponse(secondEndpoint);

        List<Integer> nestedList = nestedResponse.jsonPath().getList(comparableFields);
        List<Integer> filteredList = filteredResponse.jsonPath().getList(comparableFields);

        return nestedList.equals(filteredList);
    }
}
