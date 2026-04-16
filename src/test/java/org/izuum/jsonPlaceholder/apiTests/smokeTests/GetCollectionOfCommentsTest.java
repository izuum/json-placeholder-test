package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-003: Получение коллекции comments")
public class GetCollectionOfCommentsTest {

    @Test
    @DisplayName("TC-003: Статус-код 200")
    public void getRequestCheckStatusCode(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-003: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-003: Элементы содержат поля postId, id, name, email, body")
    public void getRequestCheckThatElementsHasFields(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments")
                .then()
                .body("size()", greaterThan(0))
                .body("every { it.containsKey('postId') }", is(true))
                .body("every { it.containsKey('id') }", is(true))
                .body("every { it.containsKey('name') }", is(true))
                .body("every { it.containsKey('email') }", is(true))
                .body("every { it.containsKey('body') }", is(true));
    }
}
