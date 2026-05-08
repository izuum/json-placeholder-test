package org.izuum.jsonPlaceholder.apiTests.nestedRoutesTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCodeGet;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-043: Получить posts пользователя")
public class GetPostsOfUserTest {

    @Test
    @DisplayName("TC-043: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCodeGet("/users/1/posts", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-043: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1/posts")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-043: Каждый элемент имеет userId=1")
    public void checkThatEveryElementsHasUserIdIsOne(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1/posts")
                .then()
                .body("userId", everyItem(equalTo(1)));
    }
}
