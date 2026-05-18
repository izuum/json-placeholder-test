package org.izuum.jsonPlaceholder.apiTests.integrationTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-103: Проверка отсутствия реальной персистентности после patch")
public class CheckLackRealPersistanceAfterPatchTest {

    @Test
    @DisplayName("TC-103: Частичная замена успешно mock-ается, но " +
            "GET-запрос не обязан возвращать измененные данные")
    public void checkLackRealPersistanceAfterGet() {
        Response getResponse = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1");

        String originalTitle = getResponse.jsonPath().getString("title");

        String newTitle = "{\"title\" : \"some title\"}";

        RestAssured.given()
                .spec(requestSpecification())
                .body(newTitle)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .statusCode(lessThan(HttpStatus.SC_INTERNAL_SERVER_ERROR));

        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", equalTo(originalTitle));
    }
}
