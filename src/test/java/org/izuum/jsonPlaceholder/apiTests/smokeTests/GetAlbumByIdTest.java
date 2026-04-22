package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-006: Получение album по id")
public class GetAlbumByIdTest {

    @Test
    @DisplayName("TC-006: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/albums/1", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-006: Ответ является объектом")
    public void getRequestCheckResponseIsAnObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums/1")
                .then()
                .body("$", instanceOf(Object.class));
    }

    @Test
    @DisplayName("TC-006: id объекта равен 1")
    public void getRequestCheckIdOfObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums/1")
                .then()
                .body("id", equalTo(1));
    }

    @Test
    @DisplayName("TC-006: Поле title не пустое")
    public void getRequestCheckTitleIsNotEmpty(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums/1")
                .then()
                .body("title", not(emptyString()));
    }
}
