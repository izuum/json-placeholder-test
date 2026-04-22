package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-029: Фильтрация todos по userId")
public class FilteringToDosByUserIdTest {

    @Test
    @DisplayName("TC-029: Статус-код 200")
    public void getRequestCheckStatusCodee(){
        checkStatusCode("/todos?userId=1", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-029: Каждый todo содержит userId = 1")
    public void checkThatAllToDosHasSpecificUserId(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos?userId=1")
                .then()
                .body("userId", everyItem(equalTo(1)));
    }
}
