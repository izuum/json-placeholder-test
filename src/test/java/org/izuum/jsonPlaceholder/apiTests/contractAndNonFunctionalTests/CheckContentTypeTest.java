package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-105: Проверка Content-Type")
public class CheckContentTypeTest {

    @Test
    @DisplayName("TC-105: Проверка возвращаемого Content-Type")
    public void checkContentType() {
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .contentType("application/json; charset=utf-8");
    }
}
