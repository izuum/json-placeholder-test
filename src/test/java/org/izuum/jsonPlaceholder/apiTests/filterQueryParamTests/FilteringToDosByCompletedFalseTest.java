package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-031: Фильтрация todos по completed = false")
public class FilteringToDosByCompletedFalseTest extends ApiTestUtils {

    @Test
    @DisplayName("TC-031: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/todos?completed=false", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-031: В каждом todo есть completed = false")
    public void checkThatAllToDosHasCompletedEqualsFalse(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos?completed=false")
                .then()
                .body("completed", everyItem(equalTo(false)));
    }
}
