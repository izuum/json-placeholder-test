package org.izuum.jsonPlaceholder.apiTests.integrationTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-104: Проверка отсутствия реального delete")
public class CheckLackRealPersistanceAfterDeleteTest {

    @Test
    @DisplayName("TC-104: DELETE-mock успешен, но GET-запрос по-прежнему отдает исходный ресурс")
    public void checkLackRealPersistanceAfterDelete() {
        Response getResponse = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1");

        String originalTitle = getResponse.jsonPath().getString("title");
        String originalBody = getResponse.jsonPath().getString("body");

        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .statusCode(lessThan(HttpStatus.SC_INTERNAL_SERVER_ERROR));

        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", equalTo(originalTitle))
                .body("body", equalTo(originalBody));
    }
}
