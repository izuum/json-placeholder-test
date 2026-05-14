package org.izuum.jsonPlaceholder.apiTests.fullReplaceData;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-076: Отправка put-запроса с несовпадающим id в url и body")
public class FullReplaceWithNoMatchersIdTest {

    @Test
    @DisplayName("TC-076: Проверка что клиент проверяет конфликтность контракта")
    public void checkThatClientCheckConflictOfContract(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("userId", 999);
        requestBody.put("id", 99);
        requestBody.put("title", "some title");
        requestBody.put("body", "some body");

        Response response = RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .when()
                .put("/posts/1");

        int responseId = response.jsonPath().getInt("id");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(1, responseId);

        System.out.println("======Фиксация поведения======");
        System.out.println("Отправлен PUT /posts/1");
        System.out.println("Статус-код ответа: " + response.getStatusCode());
        System.out.println("Тело ответа: " + response.getBody().asString());
        if(responseId == 1 ) {
            System.out.printf("Id в ответе: %d, сервер проигнорировал id в теле, конфликт не вызывал ошибку", responseId);
        } else {
            System.out.printf("Id в ответе: %d, возможен конфликт, сервер может упасть", responseId);
        }
        System.out.println();
        System.out.println("===============================");
    }
}
