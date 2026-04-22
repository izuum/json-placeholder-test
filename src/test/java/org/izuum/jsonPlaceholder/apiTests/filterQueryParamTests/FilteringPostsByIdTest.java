package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-033: Фильтрация posts по id")
public class FilteringPostsByIdTest{

    @Test
    @DisplayName("TC-033: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/posts?id=1", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-033: Длина массива полученного в ответе = 1")
    public void checkLengthOfArrayInResponse(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts?id=1")
                .then()
                .body("size()", equalTo(1));
    }

    @Test
    @DisplayName("TC-033: id полученного элемента 1")
    public void getIdOfElementInResponse(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts?id=1")
                .then()
                .body("[0].id", equalTo(1));
    }
}
