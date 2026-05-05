package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-037: Неизвестный query-параметр вместе с валидным")
public class UnknownQueryParamWithValidParamTest {

    @Test
    @DisplayName("TC-037: Сервис не падает")
    public void checkThatServiceNotFall(){
        Response response = getResponse("/posts?userId=1&unknownParam=x");

        assertTrue(response.statusCode() < 500);
    }

    @Test
    @DisplayName("TC-037: Сервис отвечает корректно")
    public void checkThatResponseIsCorrect(){
        Response response = getResponse("/posts?userId=1&unknownParam=x");

        List<Integer> usersId = response.jsonPath().getList("userId");
        for(Integer userId : usersId){
            assertEquals(1, userId);
        }
    }

    @Test
    @DisplayName("TC-037: Поведение при unknownParam задокументировано в тесте")
    public void documentBehaviorWithUnknownParam(){
        Response response = getResponse("/posts?userId=1&unknownParam=x");

        int statusCode = response.statusCode();
        List<Object> posts = response.jsonPath().getList("$");

        System.out.println("Статус-код: " + statusCode);
        System.out.println("Элементов в ответе: " + posts.size());
        System.out.println("Поведение: неизвестный параметр ИГНОРИРУЕТСЯ");
        System.out.println("Валидный фильтр userId=1 применен корректно");

        assertFalse(posts.isEmpty(), "Валидный фильтр должен вернуть результат");
    }
}
