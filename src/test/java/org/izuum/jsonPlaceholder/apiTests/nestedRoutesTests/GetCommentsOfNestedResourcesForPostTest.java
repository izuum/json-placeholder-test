package org.izuum.jsonPlaceholder.apiTests.nestedRoutesTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-039: Получить comments вложенного ресурса для post")
public class GetCommentsOfNestedResourcesForPostTest {

    @Test
    @DisplayName("TC-039: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/posts/1/comments", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-039: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1/comments")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-039: Каждый элемент имеет postId=1")
    public void checkThatEveryElementsHasPostIdIsOne(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1/comments")
                .then()
                .body("postId", everyItem(equalTo(1)));
    }
}
