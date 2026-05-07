package org.izuum.jsonPlaceholder.apiTests.negativeReadTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-059: Запрос post с негативным id")
public class GetPostByNegativeIdTest {

    @Test
    @DisplayName("TC-059: Статус-код не 5хх")
    public void checkExpectedNotServerError(){
        Response responseFirst = getResponse("/posts/-1");
        Response responseSecond = getResponse("/posts/-1");
        Response responseThird = getResponse("/posts/-1");

        assertTrue(responseFirst.statusCode() < 500);
        assertEquals(responseFirst.statusCode(), responseSecond.statusCode());
        assertEquals(responseSecond.statusCode(), responseThird.statusCode());

        assertEquals(responseFirst.getBody().asString(), responseSecond.getBody().asString());
        assertEquals(responseSecond.getBody().asString(), responseThird.getBody().asString());

        System.out.println("TC-059: ======Фиксация контракта======");
        System.out.println("Запрос: GET /posts/-1");
        System.out.println("Статус-код ожидается не 5хх, фактический: " + responseFirst.statusCode());
        System.out.println("Тело ответа ожидается {} (пустое тело), фактическое: " + responseFirst.getBody().asString());
        System.out.println("Поведение стабильно");
        System.out.println("======================================");
    }
}
