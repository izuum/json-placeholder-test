package org.izuum.jsonPlaceholder.apiTests.partUpdateTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-082: Обновить только поле body")
public class UpdateBodyInPostTest {

    @Test
    @DisplayName("TC-082: Статус-код 200")
    public void checkStatusCodeOfPatch(){
        String newBody = "{ \"body\" : \"New Body\" }";

        RestAssured.given()
                .spec(requestSpecification())
                .body(newBody)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-082: Проверка что body изменен, id и userId сохранены в ответе")
    public void checkNewBodyInPost(){
        Response oldResponse = getResponse("/posts/1");

        String newBody = "{ \"body\" : \"New Body\" }";

        RestAssured.given()
                .spec(requestSpecification())
                .body(newBody)
                .when()
                .patch("/posts/1")
                .then()
                .body("userId", equalTo(oldResponse.jsonPath().getInt("userId")))
                .body("id", equalTo(oldResponse.jsonPath().getInt("id")))
                .body("title", equalTo(oldResponse.jsonPath().getString("title")))
                .body("body", equalTo("New Body"));
    }
}
