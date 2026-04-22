package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-035: Фильтрация по несуществующему элементу")
public class FilteringByNotExistingValueTest{

    @Test
    @DisplayName("TC-035: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/posts?userId=9999", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-035: Массив в ответе пустой")
    public void checkThatArrayInResponseIsEmpty(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts?userId=9999")
                .then()
                .body("", empty())
                .body("size()", equalTo(0));
    }
}
