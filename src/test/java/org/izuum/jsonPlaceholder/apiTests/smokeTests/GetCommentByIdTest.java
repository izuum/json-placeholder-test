package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-004: Получение comment по id")
public class GetCommentByIdTest {

    @Test
    @DisplayName("TC-004: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/comments/1", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-004: Ответ является объектом")
    public void getRequestCheckResponseIsAnObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments/1")
                .then()
                .body("$", instanceOf(Object.class));
    }

    @Test
    @DisplayName("TC-004: id объекта равен 1")
    public void getRequestCheckIdOfObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments/1")
                .then()
                .body("id", equalTo(1));
    }

    @Test
    @DisplayName("TC-004: Поле email имеет тип string")
    public void getRequestCheckThatFieldEmailIsString(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments/1")
                .then()
                .body("email", instanceOf(String.class));
    }
}
