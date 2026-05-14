package org.izuum.jsonPlaceholder.apiTests.fullReplaceData;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.putRequestBody;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-078:(БАГ) Отправка put-запроса для несуществующего id")
public class SendPutRequestWithNotExistsIdTest {

    @Test
    @DisplayName("TC-078:(БАГ) Стабильный ответ сервиса, фиксация фактического статуса")
    public void checkStableAnswerOfService(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("title", "some test title");
        requestBody.put("body", "some test body");

        Response firstResponse = putRequestBody("/posts/9999", requestBody);
        Response secondResponse = putRequestBody("/posts/9999", requestBody);
        Response thirdResponse = putRequestBody("/posts/9999", requestBody);

        System.out.println("====" + firstResponse.getStatusCode() + "======");

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, firstResponse.getStatusCode());
        assertEquals(firstResponse.getStatusCode(), secondResponse.getStatusCode());
        assertEquals(secondResponse.getStatusCode(), thirdResponse.getStatusCode());

        System.out.println("======Фиксация поведения======");
        System.out.println("Отправлен PUT /posts/9999");
        System.out.println("Статус-код ответа: " + firstResponse.getStatusCode());
        System.out.println("Тело ответа: " + firstResponse.getBody().asString());
        System.out.println("Отправка Put-запроса на несуществующий ресурс вызывает ошибку сервера");
        System.out.println("Сервер показывает стектрейс в ответе");
        System.out.println("==============================");
    }
}
