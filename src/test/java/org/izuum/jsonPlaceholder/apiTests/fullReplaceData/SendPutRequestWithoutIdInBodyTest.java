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
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-075: Отправка put-запроса без id в теле")
public class SendPutRequestWithoutIdInBodyTest {

    @Test
    @DisplayName("TC-075: Статус-код не 5хх")
    public void checkThatStatusCodeNotServerError(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 999);
        requestBody.put("title", "some title");
        requestBody.put("body", "some body");

        Response response = RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .when()
                .put("/posts/1");

        assertTrue(response.getStatusCode() < HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(1, response.jsonPath().getInt("id"));

        System.out.println("======Фиксация поведения======");
        System.out.println("Отправка PUT /posts/1");
        System.out.println("Статус-код ответа: " + response.getStatusCode());
        System.out.println("id в ответе: " + response.jsonPath().getInt("id"));
        System.out.println("Тело ответа: " + response.getBody().asString());
        System.out.println("==============================");
    }
}
