package org.izuum.jsonPlaceholder.apiTests.dataDrivenTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-124: Параметризованный запрос GET для /posts с фильтрацией по пользователям по id=1..5")
public class ParameterizedRunFilteringByUserTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("TC-124: Проверка что все элементы массива соответствуют фильтрации")
    public void parameterizedGetPostWithFilteringByUser(int userId) {
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts?userId=" + userId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("userId", everyItem(equalTo(userId)));
    }
}
