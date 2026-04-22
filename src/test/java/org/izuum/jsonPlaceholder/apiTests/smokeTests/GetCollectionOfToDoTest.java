package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-009: Получение коллекции todos")
public class GetCollectionOfToDoTest {

    @Test
    @DisplayName("TC-009: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/todos", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-009: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos")
                .then()
                .body("$", instanceOf(List.class));

    }

    @Test
    @DisplayName("TC-009: Элементы содержат поля userId, id, title, completed")
    public void getRequestCheckThatElementsHasFields(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos")
                .then()
                .body("every {it.containsKey('userId')}", is(true))
                .body("every {it.containsKey('id')}", is(true))
                .body("every {it.containsKey('title')}", is(true))
                .body("every {it.containsKey('completed')}", is(true));
    }
}
