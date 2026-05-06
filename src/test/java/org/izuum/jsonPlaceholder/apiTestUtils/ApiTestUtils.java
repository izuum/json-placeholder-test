package org.izuum.jsonPlaceholder.apiTestUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

public class ApiTestUtils {

    //Метод для проверки статус-кода
    public static void checkStatusCode(String endpoint, int statusCode){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode);
    }

    // Метод для получения тела ответа, для последующего использования
    public static Response getResponse(String endpoint){
        return RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get(endpoint);
    }

    // Метод проверки наличия тех или иных полей в связных сущностях и фильтрациях
    public static boolean checkSetsOfId(String firstEndpoint, String secondEndpoint, String comparableFields){
        Response nestedResponse = getResponse(firstEndpoint);
        Response filteredResponse = getResponse(secondEndpoint);

        List<Integer> nestedList = nestedResponse.jsonPath().getList(comparableFields);
        List<Integer> filteredList = filteredResponse.jsonPath().getList(comparableFields);

        return nestedList.equals(filteredList);
    }
}
