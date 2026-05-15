package org.izuum.jsonPlaceholder.apiTests.partUpdateTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.patchRequestBody;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-086: Обновление post для несуществующего id")
public class UpdatePostWithNotExistsIdTest {

    @Test
    @DisplayName("TC-086: Стабильный ответ сервера")
    public void checkStableServerAnswer(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("title", "some title test text for not exists id");
        requestBody.put("body", "some body test text for not exists id");

        Response firstResponse = patchRequestBody("/posts/9999", requestBody);
        Response secondResponse = patchRequestBody("/posts/9999", requestBody);
        Response thirdResponse = patchRequestBody("/posts/9999", requestBody);

        assertEquals(HttpStatus.SC_OK, firstResponse.getStatusCode());
        assertEquals(HttpStatus.SC_OK, secondResponse.getStatusCode());
        assertEquals(HttpStatus.SC_OK, thirdResponse.getStatusCode());

        assertEquals(firstResponse.getBody().asString(), secondResponse.getBody().asString());
        assertEquals(secondResponse.getBody().asString(), thirdResponse.getBody().asString());

        System.out.println("======Фиксация поведения======");
        System.out.println("Отправлен PATCH /posts/1");
        System.out.println("Статус-код ответа: " + firstResponse.getStatusCode());
        System.out.println("Тело ответа: " + firstResponse.getBody().asString());
        System.out.println("Отправленные поля записываются в id");
        System.out.println("==============================");
    }
}
