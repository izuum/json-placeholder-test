package org.izuum.jsonPlaceholder.apiTests.dataDrivenTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-120: Параметризованный запрос GET для /users по id=1..10")
public class ParameterizedRunGetUserTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("TC-120: Проверка что каждый запрос по id возвращает валидный user")
    public void parameterizedGetUserTest(int userId) {
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(userId))
                .body("name", notNullValue())
                .body("username", notNullValue())
                .body("email", notNullValue())
                .body("address", notNullValue())
                .body("phone", notNullValue())
                .body("website", notNullValue())
                .body("company", notNullValue());
    }
}
