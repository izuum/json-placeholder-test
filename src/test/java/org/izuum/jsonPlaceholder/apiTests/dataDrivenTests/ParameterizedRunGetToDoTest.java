package org.izuum.jsonPlaceholder.apiTests.dataDrivenTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-119: Параметризованный запрос GET для /todos по id=1..10")
public class ParameterizedRunGetToDoTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("TC-119: Проверка что каждый запрос по id возвращает валидный todo")
    public void parameterizedGetToDoTest(int todoId){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos/" + todoId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(todoId))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("completed", isA(Boolean.class));
    }
}
