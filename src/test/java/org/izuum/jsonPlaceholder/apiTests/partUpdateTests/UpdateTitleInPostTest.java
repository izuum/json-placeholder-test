package org.izuum.jsonPlaceholder.apiTests.partUpdateTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-081: Обновить только поле title")
public class UpdateTitleInPostTest {

    @Test
    @DisplayName("TC-081: Статус-код 200")
    public void checkStatusCodeOfPatch(){
        String newTitle = "{ \"title\" : \"New Title\" }";

        RestAssured.given()
                .spec(requestSpecification())
                .body(newTitle)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-081: Проверка title, остальные поля присутствуют")
    public void checkNewTitleInPost(){
        String newTitle = "{ \"title\" : \"New Title\" }";

        RestAssured.given()
                .spec(requestSpecification())
                .body(newTitle)
                .when()
                .patch("/posts/1")
                .then()
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", equalTo("New Title"))
                .body("body", notNullValue());
    }
}
