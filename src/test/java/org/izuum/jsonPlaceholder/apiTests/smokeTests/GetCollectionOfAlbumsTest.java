package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-005: Получение коллекции albums")
public class GetCollectionOfAlbumsTest {

    @Test
    @DisplayName("TC-005: Статус-код 200")
    public void getRequestCheckStatusCode(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-005: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-005: Элементы содержат поля userId, id, title")
    public void getRequestCheckThatElementsHasFields(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums")
                .then()
                .body("size()", greaterThan(0))
                .body("every { it.containsKey('userId') }", is(true))
                .body("every { it.containsKey('id') }", is(true))
                .body("every { it.containsKey('title') }", is(true));
    }
}
