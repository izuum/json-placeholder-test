package org.izuum.jsonPlaceholder.apiTests.dataDrivenTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-121: Параметризованный запрос GET для /users/{id}/posts по id=1..5")
public class ParameterizedRunGetUserWithNestedPostsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("TC-121: Проверка что у всех элементов userId совпадает с параметром id")
    public void parameterizedGetUserWithNestedPostsTest(int userId){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/" + userId + "/posts")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("userId", everyItem(equalTo(userId)));
    }
}
