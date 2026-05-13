package org.izuum.jsonPlaceholder.apiTests.createResources;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-071: Создание comments с полями postId, name, email, body")
public class CreateCommentWithFieldsTest {

    @Test
    @DisplayName("TC-071: Успешное создание mock-create")
    public void checkThatSuccessMockCreate(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("postId", 1);
        requestBody.put("name", "test name John Slow");
        requestBody.put("email", "testemail@john.slow");
        requestBody.put("body", "test body John Slow");

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .post("/comments")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @DisplayName("TC-071: Провера JSON-ответа на корректность")
    public void checkThatJsonResponseIsCorrect(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("postId", 1);
        requestBody.put("name", "test name John Slow");
        requestBody.put("email", "testemail@john.slow");
        requestBody.put("body", "test body John Slow");

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .post("/comments")
                .then()
                .body("postId", equalTo(requestBody.get("postId")))
                .body("name", equalTo(requestBody.get("name")))
                .body("email", equalTo(requestBody.get("email")))
                .body("body", equalTo(requestBody.get("body")));
    }

}
