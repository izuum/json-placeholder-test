package org.izuum.jsonPlaceholder.apiTests.partUpdateTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-087: Обновление поля completed в todos из false в true")
public class UpdateCompletedToTrueTest {

    @Test
    @DisplayName("TC-087: Статус-код 200, проверка поля completed")
    public void checkStatusCodeOfPatch(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("completed", true);

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .when()
                .patch("/todos/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("completed", equalTo(true));
    }
}
