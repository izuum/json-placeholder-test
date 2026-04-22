package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;

@DisplayName("TC-036: Некорректное значение boolean-фильтра")
public class IncorrectValueOfBooleanFilterTest{

    @Test
    @DisplayName("TC-036: Проверка что ответ стабилен")
    public void getRequestCheckStatusCodeMultipleRequest(){
        Response responseOne = getResponse("/todos?completed=invalid");
        Response responseTwo = getResponse("/todos?completed=invalid");
        Response responseThree = getResponse("/todos?completed=invalid");

        Assertions.assertEquals(HttpStatus.SC_OK, responseOne.getStatusCode());
        Assertions.assertEquals(responseOne.getStatusCode(), responseTwo.getStatusCode());
        Assertions.assertEquals(responseTwo.getStatusCode(), responseThree.getStatusCode());

        Assertions.assertEquals(0, responseOne.jsonPath().getList("$").size());
        Assertions.assertEquals(responseOne.jsonPath().getList("$").size(),
                responseTwo.jsonPath().getList("$").size());
        Assertions.assertEquals(responseTwo.jsonPath().getList("$").size(),
                responseThree.jsonPath().getList("$").size());
    }

}
