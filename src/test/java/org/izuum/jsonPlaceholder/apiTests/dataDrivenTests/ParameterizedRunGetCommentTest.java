package org.izuum.jsonPlaceholder.apiTests.dataDrivenTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-116: Параметризованный запрос GET для /comments по id=1..10")
public class ParameterizedRunGetCommentTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("TC-116: Проверка что каждый запрос по id возвращает валидный comment")
    public void parameterizedGetCommentsTest(int commentId){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments/" + commentId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(commentId))
                .body("postId", notNullValue())
                .body("name", notNullValue())
                .body("email", notNullValue())
                .body("body", notNullValue());
    }
}
