package org.izuum.jsonPlaceholder.apiTests.negativeReadTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasKey;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-062: Запрос post с плохим query-параметром/символом")
public class GetPostWithBadQueryParamOrSymbolTest {

    @Test
    @DisplayName("TC-062: Статус-код не 5хх")
    public void checkExpectedNotServerError(){
        Response response = getResponse("/posts/1?bad=%");

        assertTrue(response.statusCode() < 500);
    }

    @Test
    @DisplayName("TC-062: Клиент корректно кодирует запрос")
    public void checkClientCorrectRequest(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1?bad=%")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("", hasKey("userId"))
                .body("", hasKey("id"))
                .body("", hasKey("title"))
                .body("", hasKey("body"));

    }
}
