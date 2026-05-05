package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-038: Пустое значение query-параметра")
public class EmptyValueOfQueryParamTest {

    @Test
    @DisplayName("TC-038: Сервис не падает")
    public void checkThatServiceNotFall(){
        Response response = getResponse("/posts?userId=");

        assertTrue(response.statusCode() < 500);
    }

    @Test
    @DisplayName("TC-038: Сервис отвечает корректно")
    public void checkThatResponseIsCorrect(){
        Response response = getResponse("/posts?userId=");

        List<Object> posts = response.jsonPath().getList("$");
        assertTrue(posts.isEmpty());

        int statusCode = response.statusCode();

        assertTrue(statusCode == 200 || statusCode == 400);
    }

    @Test
    @DisplayName("TC-038: Фиксация ожидаемого поведения")
    public void documentRequiredBehavior(){
        Response response = getResponse("/posts?userId=");

        List<Object> posts = response.jsonPath().getList("$");
        int statusCode = response.statusCode();

        System.out.println("Сервис не падает");
        System.out.println("Статус-код: " + statusCode);
        System.out.println("В ответе массив размером: " + posts.size());
        System.out.println("В ответ на пустое значение фильтра приходит пустой ответ, сервис отвечает корректно");
    }
}
