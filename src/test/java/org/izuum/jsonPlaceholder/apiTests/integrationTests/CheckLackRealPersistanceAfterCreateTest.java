package org.izuum.jsonPlaceholder.apiTests.integrationTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-101: Проверка отсутствия реальной персистентности после create")
public class CheckLackRealPersistanceAfterCreateTest {

    @Test
    @DisplayName("TC-101: Создание успешно mock-ается, но при последующем GET" +
            "данные могут не сохраняться")
    public void checkLackRealPersistanceAfterCreate() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 101);
        requestBody.put("title", "some title");
        requestBody.put("body", "some body");

        Response postResponse = RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();

        int createdId =  postResponse.jsonPath().getInt("id");

        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/" + createdId)
                .then()
                .statusCode(anyOf(equalTo(HttpStatus.SC_OK), equalTo(HttpStatus.SC_NOT_FOUND)))
                .statusCode(lessThan(HttpStatus.SC_INTERNAL_SERVER_ERROR));
    }
}
