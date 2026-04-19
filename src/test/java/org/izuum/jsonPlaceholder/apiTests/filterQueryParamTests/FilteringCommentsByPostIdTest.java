package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.geom.RectangularShape;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-026: Фильтрация comments по postId")
public class FilteringCommentsByPostIdTest {

    @Test
    @DisplayName("TC-026: Статус-код 200")
    public void getRequestCheckStatusCode(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments?postId=1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-026: Все элементы comment имеют postId = 1")
    public void checkThatAllElementsOfCommentHasSpecificPostId(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments?postId=1")
                .then()
                .body("postId", everyItem(equalTo(1)));
    }
}
