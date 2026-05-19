package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-110: Проверка email на базовый шаблон")
public class CheckEmailForBaseTemplateTest {

    @Test
    @DisplayName("TC-110: Проверка что email соответствует простому regex команды")
    public void checkThatEmailMatchesTemplate(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("email", both(isA(String.class)).and(notNullValue()))
                .body("email", not(emptyString()))
                .body("email", containsString("@"))
                .body("email", not(containsString(" ")));
    }
}
