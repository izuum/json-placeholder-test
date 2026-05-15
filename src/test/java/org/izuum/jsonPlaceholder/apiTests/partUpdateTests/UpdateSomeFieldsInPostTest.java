package org.izuum.jsonPlaceholder.apiTests.partUpdateTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-083: Обновить несколько полей сразу")
public class UpdateSomeFieldsInPostTest {

    @Test
    @DisplayName("TC-083: Статус-код 200")
    public void checkStatusCodeOfPatch(){
        String newFields = "{\"userId\" : 5, \"body\" : \"new test body\"}";

        RestAssured.given()
                .spec(requestSpecification())
                .body(newFields)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-083: Проверка что все отправленные поля отражены")
    public void checkThatAllSentFieldsAreThere(){
        Response oldResponse = getResponse("/posts/1");

        String newFields = "{\"userId\" : 5, \"body\" : \"new test body\"}";

        RestAssured.given()
                .spec(requestSpecification())
                .body(newFields)
                .when()
                .patch("/posts/1")
                .then()
                .body("userId", equalTo(5))
                .body("id", equalTo(oldResponse.jsonPath().getInt("id")))
                .body("title", equalTo(oldResponse.jsonPath().getString("title")))
                .body("body", equalTo("new test body"));
    }
}
