package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-008: Получение photo по id")
public class GetPhotoByIdTest {

    @Test
    @DisplayName("TC-008: Статус-код 200")
    public void getRequestCheckStatusCode(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-008: Ответ является объектом")
    public void getRequestCheckResponseIsAnObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos/1")
                .then()
                .body("$", instanceOf(Object.class));
    }

    @Test
    @DisplayName("TC-008: id объекта равен 1")
    public void getRequestCheckIdOfObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos/1")
                .then()
                .body("id", equalTo(1));
    }

    @Test
    @DisplayName("TC-008: Поля url и thumbnailUrl являются строками")
    public void getRequestCheckThatUrlAndThumbnailUrlAreString(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos/1")
                .then()
                .body("url", instanceOf(String.class))
                .body("thumbnailUrl", instanceOf(String.class));
    }
}
