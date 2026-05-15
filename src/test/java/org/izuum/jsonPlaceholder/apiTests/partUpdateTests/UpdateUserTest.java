package org.izuum.jsonPlaceholder.apiTests.partUpdateTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-088: Обновление простого поля в user")
public class UpdateUserTest {

    @Test
    @DisplayName("TC-088: Проверка что сервис отвечает JSON")
    public void checkThatServerAnswerJson(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "UpdateUserName");
        requestBody.put("email", "UpdateUserEmail");

        Response response = RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .when()
                .patch("/users/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(1))
                .body("name", equalTo("UpdateUserName"))
                .body("email", equalTo("UpdateUserEmail"))
                .body("address", notNullValue())
                .body("phone", notNullValue())
                .body("website", notNullValue())
                .body("company", notNullValue())
                .extract().response();

        System.out.println("======Фиксация поведения======");
        System.out.println("Отправлен PATCH /users/1");
        System.out.println("Статус-код ответа: " + response.getStatusCode());
        System.out.println("Тело ответа: " + response.getBody().asString());
        System.out.println("Обновленное имя: " + response.jsonPath().getString("name"));
        System.out.println("Обновленный email: " + response.jsonPath().getString("email"));
        System.out.println("Вложеная структура не ломается");
        System.out.println("==============================");
    }
}
