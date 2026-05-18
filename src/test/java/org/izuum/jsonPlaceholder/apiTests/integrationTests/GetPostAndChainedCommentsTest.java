package org.izuum.jsonPlaceholder.apiTests.integrationTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-098: Сравнение полученного post и comments относящихся к post")
public class GetPostAndChainedCommentsTest {

    @Test
    @DisplayName("TC-098: Проверка что post.id совпадает с postId во всех comments")
    public void getPostAndChainedComments() {
        Response postsResponse = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("posts/1");

        int postId = postsResponse.jsonPath().getInt("id");

        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("posts/" + postId + "/comments")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("postId", everyItem(equalTo(postId)))
                .body("$", not(empty()));
    }
}
