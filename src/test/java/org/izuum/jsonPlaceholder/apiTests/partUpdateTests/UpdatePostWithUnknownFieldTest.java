package org.izuum.jsonPlaceholder.apiTests.partUpdateTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.patchRequestBody;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-085: Обновление post с неизвестным полем")
public class UpdatePostWithUnknownFieldTest {

    @Test
    @DisplayName("TC-085: Стабильный ответ сервера")
    public void checkThatServerStableAnswer(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("extraStringField", "some extra test text for extraStringField");
        requestBody.put("extraIntegerField", 5567548);

        Response firstResponse = patchRequestBody("/posts/1", requestBody);
        Response secondResponse = patchRequestBody("/posts/1", requestBody);
        Response thirdResponse = patchRequestBody("/posts/1", requestBody);

        assertEquals(HttpStatus.SC_OK, firstResponse.getStatusCode());
        assertEquals(HttpStatus.SC_OK, secondResponse.getStatusCode());
        assertEquals(HttpStatus.SC_OK, thirdResponse.getStatusCode());

        assertEquals(firstResponse.getBody().asString(), secondResponse.getBody().asString());
        assertEquals(secondResponse.getBody().asString(), thirdResponse.getBody().asString());

        assertEquals(requestBody.get("extraStringField"),
                firstResponse.jsonPath().getString("extraStringField"));
        assertEquals(requestBody.get("extraIntegerField"),
                firstResponse.jsonPath().getInt("extraIntegerField"));
        assertEquals(firstResponse.jsonPath().getString("extraStringField"),
                secondResponse.jsonPath().getString("extraStringField"));
        assertEquals(firstResponse.jsonPath().getInt("extraIntegerField"),
                secondResponse.jsonPath().getInt("extraIntegerField"));
        assertEquals(secondResponse.jsonPath().getString("extraStringField"),
                thirdResponse.jsonPath().getString("extraStringField"));
        assertEquals(secondResponse.jsonPath().getInt("extraIntegerField"),
                thirdResponse.jsonPath().getInt("extraIntegerField"));

        System.out.println("======Фиксация поведения======");
        System.out.println("Отправлен PATCH /posts/1");
        System.out.println("Статус-код ответа: " + firstResponse.getStatusCode());
        System.out.println("Тело ответа: " + firstResponse.getBody().asString());
        System.out.println("extraFiled добавляются в ресурс");
        System.out.println("Сервис отвечает стабильно");
        System.out.println("===============================");
    }
}
