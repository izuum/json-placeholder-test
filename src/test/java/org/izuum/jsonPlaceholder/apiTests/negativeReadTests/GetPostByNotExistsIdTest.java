package org.izuum.jsonPlaceholder.apiTests.negativeReadTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-052: Запрос post с несуществующим id")
public class GetPostByNotExistsIdTest {

    @Test
    @DisplayName("TC-052: Ожидается ошибка 404 и пустое тело объекта")
    public void checkExpectedNotFoundAndEmptyBody(){
        Response response = getResponse("/posts/9999");

        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
        assertEquals("{}", response.getBody().asString());

        System.out.println("TC-052: =====Фиксация контракта=====");
        System.out.println("Запрос: GET /posts/9999");
        System.out.println("Статус код ожидается: 404, фактический: " + response.statusCode());
        System.out.println("Тело ответа ожидается: {} (пустой объект), фактическое: " + response.getBody().asString());
        System.out.println("====================================");
    }
}
