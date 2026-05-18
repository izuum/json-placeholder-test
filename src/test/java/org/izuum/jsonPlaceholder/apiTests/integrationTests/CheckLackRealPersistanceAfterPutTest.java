package org.izuum.jsonPlaceholder.apiTests.integrationTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-102: Проверить отсутствие реальной персистентности после put")
public class CheckLackRealPersistanceAfterPutTest {

    @Test
    @DisplayName("TC-102: Замена успешно mock-ается, но GET-запрос" +
            "не обязан возвращать измененные данные")
    public void checkLackRealPersistanceAfterPut() {
        Response getResponse = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1");

        int originalUserId = getResponse.jsonPath().getInt("userId");
        String originalTitle = getResponse.jsonPath().getString("title");
        String originalBody = getResponse.jsonPath().getString("body");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 101);
        requestBody.put("title", "some title");
        requestBody.put("body", "some body");

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .statusCode(lessThan(HttpStatus.SC_INTERNAL_SERVER_ERROR));

        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("userId", equalTo(originalUserId))
                .body("title",equalTo(originalTitle))
                .body("body", equalTo(originalBody));
    }
}
