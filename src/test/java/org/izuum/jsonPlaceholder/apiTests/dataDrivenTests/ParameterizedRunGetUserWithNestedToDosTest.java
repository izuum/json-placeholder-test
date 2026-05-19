package org.izuum.jsonPlaceholder.apiTests.dataDrivenTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-123: Параметризованный запрос GET для /users/{id}/todos по id=1..5")
public class ParameterizedRunGetUserWithNestedToDosTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("TC-123: Проверка что у всех элементов userId совпадает c параметром id")
    public void parameterizedGetUserWithNestedTodosTest(int userId) {
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/" + userId + "/todos")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("userId", everyItem(equalTo(userId)));
    }
}
