package org.izuum.jsonPlaceholder.apiTests.dataDrivenTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-115: Параметризованный запрос GET для /posts по id=1..10")
public class ParameterizedRunGetPostTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("TC-115: Проверка что каждый запрос по id возвращает" +
            " валидный объект и совпадающий id")
    public void parameterizedGetPostTest(int postId) {
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(postId))
                .body("title", notNullValue())
                .body("body", notNullValue())
                .body("userId", notNullValue());
    }
}
