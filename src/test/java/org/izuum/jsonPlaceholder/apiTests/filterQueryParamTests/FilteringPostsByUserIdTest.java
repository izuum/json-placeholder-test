package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-025: Фильтрация posts по userId")
public class FilteringPostsByUserIdTest {

    @Test
    @DisplayName("TC-025: Статус-код 200")
    public void getRequestCheckStatusCode(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts?userId=1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-025: Все элементы posts имеют userId = 1")
    public void checkThatAllElementsOfPostsHasSpecificUserId(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts?userId=1")
                .then()
                .body("userId", everyItem(equalTo(1)));
    }
}
