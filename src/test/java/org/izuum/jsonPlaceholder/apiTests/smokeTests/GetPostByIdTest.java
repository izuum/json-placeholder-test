package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-002: Получение конкретного post по id")
public class GetPostByIdTest {

    @Test
    @DisplayName("TC-002: Статус-код 200")
    public void getRequestCheckStatusCode(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-002: Ответ является объектом")
    public void getRequestCheckResponseIsAnObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .body("", instanceOf(Object.class));
    }

    @Test
    @DisplayName("TC-002: id объекта равен 1")
    public void getRequestCheckIdOfObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .body("id", equalTo(1));
    }

    @Test
    @DisplayName("TC-002: В объекте присутствуют обязательные поля")
    /*
     * Из тест-набора не очень понятно, что подразумевается под обязательными полями
     */
    public void getRequestCheckThanObjectHasRequiredFields(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .body("", hasKey("id"))
                .body("", hasKey("title"))
                .body("", hasKey("body"))
                .body("", hasKey("userId"));
    }
}
