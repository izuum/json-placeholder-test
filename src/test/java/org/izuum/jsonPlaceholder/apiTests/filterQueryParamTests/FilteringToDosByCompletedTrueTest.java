package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-030: Фильтрация todos по completed = true")
public class FilteringToDosByCompletedTrueTest {

    @Test
    @DisplayName("TC-030: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/todos?completed=true", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-030: В каждом todo есть completed = true")
    public void checkThatAllToDosHasCompletedEqualsTrue(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos?completed=true")
                .then()
                .body("completed", everyItem(equalTo(true)));
    }
}
