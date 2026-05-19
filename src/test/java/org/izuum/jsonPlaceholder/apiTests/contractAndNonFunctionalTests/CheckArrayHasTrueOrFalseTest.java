package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-111: Проверка что массив содержит как true так и false для completed")
public class CheckArrayHasTrueOrFalseTest {

    @Test
    @DisplayName("TC-111: Проверка что данные пригодны для позитивной параметризации фильтров")
    public void checkThatArrayHasTrueOrFalse(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos")
                .then()
                .body("completed", everyItem(isA(Boolean.class)))
                .body("completed", not(hasItem(nullValue())))
                .body("completed", hasItem(Boolean.TRUE))
                .body("completed", hasItem(Boolean.FALSE));
    }
}
