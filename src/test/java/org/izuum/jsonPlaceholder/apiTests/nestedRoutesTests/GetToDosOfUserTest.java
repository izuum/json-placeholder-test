package org.izuum.jsonPlaceholder.apiTests.nestedRoutesTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCodeGet;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-042: Получение todos пользователя")
public class GetToDosOfUserTest {

    @Test
    @DisplayName("TC-042: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCodeGet("/users/1/todos", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-042: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1/todos")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-042: Каждый элемент имеет userId=1")
    public void checkThatEveryElementsHasUserIdIsOne(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1/todos")
                .then()
                .body("userId", everyItem(equalTo(1)));
    }
}
