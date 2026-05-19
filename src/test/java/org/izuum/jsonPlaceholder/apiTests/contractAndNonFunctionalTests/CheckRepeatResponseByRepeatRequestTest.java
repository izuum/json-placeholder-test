package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-113: Проверка повторяемости ответа при двух подряд запросах")
public class CheckRepeatResponseByRepeatRequestTest {

    @Test
    @DisplayName("TC-113: Проверка идентичности данных между запросами")
    public void checkRepeatResponseByRepeatRequest() {
        Response response = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts");

        Response responseTwo = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts");

        assertEquals(response.getStatusCode(), responseTwo.getStatusCode());
        assertEquals(response.getBody().asString(), responseTwo.getBody().asString());
    }
}
