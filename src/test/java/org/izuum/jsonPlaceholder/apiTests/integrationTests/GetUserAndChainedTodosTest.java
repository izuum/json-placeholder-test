package org.izuum.jsonPlaceholder.apiTests.integrationTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-100: Сравнение полученного user и todo относящиеся к user")
public class GetUserAndChainedTodosTest {

    @Test
    @DisplayName("TC-100: Проверка что каждый todo.userId совпадает с user.id")
    public void getUserAndChainedTodosTest() {
        Response userResponse = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("users/1");

        int userId = userResponse.jsonPath().getInt("id");

        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("users/" + userId + "/todos")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("userId", everyItem(equalTo(userId)))
                .body("$", not(empty()));
    }
}
