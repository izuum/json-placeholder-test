package org.izuum.jsonPlaceholder.apiTests.negativeReadTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-051: Запрос post с id вне ожидаемого диапазона")
public class GetPostByIdOutOfExpectedRangeTest {

    @Test
    @DisplayName("TC-051: Ожидается статус-код не 200 с валидным объектом")
    public void checkThatExpectedStatusCodeNotSuccessWithValidObject(){
        Response response = getResponse("/posts/0");

        assertNotEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("{}", response.getBody().asString());

        System.out.println("Запрос GET /post/0");
        System.out.println("Статус-код: " + response.statusCode());
        System.out.println("Тело ответа: " + response.getBody().asString());
    }
}
