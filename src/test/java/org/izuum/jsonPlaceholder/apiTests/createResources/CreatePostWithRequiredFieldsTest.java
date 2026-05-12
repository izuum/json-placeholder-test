package org.izuum.jsonPlaceholder.apiTests.createResources;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCodePost;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-064: Создание post только с обязательными полями")
public class CreatePostWithRequiredFieldsTest {

    @Test
    @DisplayName("TC-064: Статус-код 201")
    public void checkThatStatusCodeCreated(){
        checkStatusCodePost("/posts", HttpStatus.SC_CREATED);
    }

    @Test
    @DisplayName("TC-064: Сервис возвращает mock-object, отсутствует реальная персистентность")
    public void checkThatServiceReturnMock(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "test title");
        requestBody.put("body", "test body");
        requestBody.put("userId", "1");

        Response postResponse = RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .when()
                .post("/posts");

        assertEquals(HttpStatus.SC_CREATED, postResponse.statusCode());
        int createdId = postResponse.jsonPath().getInt("id");

        Response getResponse = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/" + createdId);

        assertEquals(HttpStatus.SC_NOT_FOUND, getResponse.statusCode());

        System.out.println("=========Персистентность отсутствует=========");
        System.out.println("POST /posts -> 201 (Created)");
        System.out.println("GET /posts/101 -> " + getResponse.statusCode() + (" (реально не сохранен)"));
        System.out.println("Данные не сохранены");
        System.out.println("=============================================");

    }

}
