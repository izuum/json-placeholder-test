package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-027: Фильтрация albums по userId")
public class FilteringAlbumsByUserIdTest {

    @Test
    @DisplayName("TC-027: Статус-код 200")
    public void getRequestCheckStatusCode(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums?userId=1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-027: Каждый album принедлежит userId = 1")
    public void checkThatAllAlbumsBelongsToSpecificUserId(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums?userId=1")
                .then()
                .body("userId", everyItem(equalTo(1)));
    }
}
