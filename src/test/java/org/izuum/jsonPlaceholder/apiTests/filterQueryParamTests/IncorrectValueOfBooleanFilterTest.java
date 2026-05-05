package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-036: Некорректное значение boolean-фильтра")
public class IncorrectValueOfBooleanFilterTest{

    @Test
    @DisplayName("TC-036: Проверка стабильности ответа")
    public void getRequestCheckStatusCodeMultipleRequest(){
        Response responseOne = getResponse("/todos?completed=invalid");
        Response responseTwo = getResponse("/todos?completed=invalid");
        Response responseThree = getResponse("/todos?completed=invalid");

        assertEquals(HttpStatus.SC_OK, responseOne.getStatusCode());
        assertEquals(responseOne.getStatusCode(), responseTwo.getStatusCode());
        assertEquals(responseTwo.getStatusCode(), responseThree.getStatusCode());

        assertEquals(0, responseOne.jsonPath().getList("$").size());
        assertEquals(responseOne.jsonPath().getList("$").size(),
                responseTwo.jsonPath().getList("$").size());
        assertEquals(responseTwo.jsonPath().getList("$").size(),
                responseThree.jsonPath().getList("$").size());
    }

    @Test
    @DisplayName("TC-036: Сервис не падает")
    public void checkThatServiceNotFall(){
        Response response = getResponse("/todos?completed=invalid");

        assertTrue(response.statusCode() < 500);
    }

    @Test
    @DisplayName("TC-036: Ожидается пустой массив или игнорирование фильтра в ожидаемом поведении")
    public void checkThatResponseIsAnEmptyArray(){
        Response response = getResponse("/todos?completed=invalid");

        int size = response.jsonPath().getList("$").size();

        assertEquals(0, size);
    }
}
