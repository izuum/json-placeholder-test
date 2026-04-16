package org.izuum.jsonPlaceholder.apiTests.smokeTests;


import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-007: Получение коллекции photos")
public class GetCollectionsOfPhotosTest {

    @Test
    @DisplayName("TC-007: Статус-код 200")
    public void getRequestCheckStatusCode(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-007: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-007: Элементы массива содержат поля albumId, id, title, url, thumbnailUrl")
    public void getRequestCheckThatElementsHasFields(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos")
                .then()
                .body("every {it.containsKey('albumId')}", is(true))
                .body("every {it.containsKey('id')}", is(true))
                .body("every {it.containsKey('title')}", is(true))
                .body("every {it.containsKey('url')}", is(true))
                .body("every {it.containsKey('thumbnailUrl')}", is(true));
    }

}
