package org.izuum.jsonPlaceholder.apiTests.integrationTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-097: Сравнение полученного user и posts относящиеся к этому user")
public class GetUserAndChainedPostsTest {

    @Test
    @DisplayName("TC-097: id пользователя используется во всех posts, данные консистентны")
    public void getUserAndChainedPosts() {
        Response userResponse = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1/");

        int userId = userResponse.jsonPath().getInt("id");

        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/" + userId + "/posts")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("userId", everyItem(equalTo(userId)))
                .body("$", not(empty()));
    }
}
