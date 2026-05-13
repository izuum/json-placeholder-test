package org.izuum.jsonPlaceholder.apiTests.createResources;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-072: Создание todos с полем completed=true")
public class CreateTodosWithCompletedIsTrueTest {

    @Test
    @DisplayName("TC-072: Успешное создание mock-create")
    public void checkThatSuccessCreateTodosWithCompletedIsTrue() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("title", "to do something for testing");
        requestBody.put("completed", true);

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .post("/todos")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @DisplayName("TC-072: Проверка что completed в ответе обрабатывается корректно")
    public void checkThatFieldCompletedIsTrue() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("title", "to do something for testing");
        requestBody.put("completed", true);

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .post("/todos")
                .then()
                .body("id", notNullValue())
                .body("userId", equalTo(requestBody.get("userId")))
                .body("title", equalTo(requestBody.get("title")))
                .body("completed", equalTo(true));
    }
}
