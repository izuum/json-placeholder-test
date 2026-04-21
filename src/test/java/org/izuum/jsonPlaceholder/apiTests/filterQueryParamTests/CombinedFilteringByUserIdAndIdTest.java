package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-034: Комбинация нескольких фильтров")
public class CombinedFilteringByUserIdAndIdTest{

    @Test
    @DisplayName("TC-034: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/posts?userId=1&id=1", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-034: Все элементы удовлетворяют обоим условиям")
    public void checkThatAllElementsSatisfyBothConditions(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts?userId=1&id=1")
                .then()
                .body("size()", equalTo(1))
                .body("[0].userId", equalTo(1))
                .body("[0].id", equalTo(1));
    }
}
