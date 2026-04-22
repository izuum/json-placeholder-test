package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-010: Получение todo по id")
public class GetToDoByIdTest {

    @Test
    @DisplayName("TC-010: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/todos/1" , HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-010: Ответ является объектом")
    public void getRequestCheckResponseIsAnObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos/1")
                .then()
                .body("$", instanceOf(Object.class));
    }

    @Test
    @DisplayName("TC-010: Поле completed имеет тип boolean")
    public void getRequestCheckFieldCompletedIsBoolean(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos/1")
                .then()
                .body("completed", instanceOf(Boolean.class));
    }
}
