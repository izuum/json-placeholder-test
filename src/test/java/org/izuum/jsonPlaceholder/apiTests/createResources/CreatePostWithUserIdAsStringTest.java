package org.izuum.jsonPlaceholder.apiTests.createResources;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.postRequestBody;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-70: Создание post с полем userId как строкой вместо числа")
public class CreatePostWithUserIdAsStringTest {

    @Test
    @DisplayName("TC-070: Проверка, что типы в mock-response не ломают парсинг клиента")
    public void checkThatMockResponseNotBreakParsingClient(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("title", "test title");
        requestBody.put("body", "test body");
        requestBody.put("userId", "1");

        Response response = postRequestBody("/posts",  requestBody);

        int statusCode = response.getStatusCode();
        Object userId = response.jsonPath().get("userId");

        assertNotNull(userId);
        assertTrue(statusCode < 500);
        assertEquals(201, statusCode);

        System.out.println("======Фиксация поведения======");
        System.out.println("Статус-код в ответе: " + statusCode);
        System.out.println("Тело ответа: " + response.getBody().asString());
        System.out.println("UserId имеет тип: " + userId.getClass().getSimpleName());
        System.out.println("Сервер не падает");
        System.out.println("==============================");
    }

}
