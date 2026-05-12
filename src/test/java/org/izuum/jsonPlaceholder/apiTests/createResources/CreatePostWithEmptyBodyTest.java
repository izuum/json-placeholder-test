package org.izuum.jsonPlaceholder.apiTests.createResources;

import groovy.json.JsonOutput;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.postRequestBody;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-066: Создание post с пустым телом запроса")
public class CreatePostWithEmptyBodyTest {
    Map<String, Object> emptyRequestBody = new HashMap<>();

    @Test
    @DisplayName("TC-066: Статус-код не 5хх")
    public void checkThatStatusCodeNotServerError(){
        Response response = postRequestBody("/posts", emptyRequestBody);
        assertTrue(response.getStatusCode() < 500);
    }

    @Test
    @DisplayName("TC-066: Фиксация ответа и контракта")
    public void checkResponseOfEmptyBody(){
        Response response = postRequestBody("/posts", emptyRequestBody);

        System.out.println("======TC-066: Контракт зафиксирован======");
        assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
        System.out.println("Статус-код ответа: " + response.getStatusCode());
        assertEquals("101", response.jsonPath().get("id").toString());
        System.out.println("Ожидаемый id=101, фактический id=" + response.jsonPath().get("id"));
        System.out.println("Тело ответа: " + response.getBody().asString());
        System.out.println("Сервер создает ресурс с id=101 и без полей");
        System.out.println("=========================================");
    }
}
