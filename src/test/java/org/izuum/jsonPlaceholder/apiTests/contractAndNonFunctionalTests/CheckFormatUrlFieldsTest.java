package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-109: Проверка формата URL-полей")
public class CheckFormatUrlFieldsTest {

    @Test
    @DisplayName("TC-109: Проверка что у photo url и thumbnailUrl начинаются с http")
    public void checkFormatUrlFields(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("url", both(isA(String.class)).and(notNullValue()))
                .body("url", startsWith("http"))
                .body("url", containsString("://"))
                .body("thumbnailUrl", both(isA(String.class)).and(notNullValue()))
                .body("thumbnailUrl", startsWith("http"))
                .body("thumbnailUrl", containsString("://"));
    }
}
