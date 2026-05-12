package org.izuum.jsonPlaceholder.apiTests.createResources;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCodePost;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-063: Создаение post с полным валидным body")
public class CreatePostWithValidBodyTest {

    @Test
    @DisplayName("TC-063: Статус-код 201")
    public void checkThatStatusCodeCreated(){
        checkStatusCodePost("/posts", HttpStatus.SC_CREATED);
    }

    @Test
    @DisplayName("TC-063: В ответе есть id=101 и отправленные поля")
    public void checkThatResponseHasSentFields(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", "1");
        requestBody.put("title", "test title");
        requestBody.put("body", "test body");

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType("application/json; charset=UTF-8")
                .body("id", equalTo(101))
                .body("title", equalTo("test title"))
                .body("body", equalTo("test body"))
                .body("userId", equalTo("1"));
    }
}
