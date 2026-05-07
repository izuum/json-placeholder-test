package org.izuum.jsonPlaceholder.apiTests.negativeReadTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-061: Запрос post с некорректным путем с двойным слешем")
public class GetPostWithIncorrectPathDoubleSlashTest {

    @Test
    @DisplayName("TC-061: Статус-код не 5хх")
    public void checkExpectedNotServerError(){
        Response response = getResponse("/posts//1");

        assertTrue(response.statusCode() < 500);

        System.out.println("TC-061: ======Фиксация контракта======");
        System.out.println("Запрос: GET /posts//1");
        System.out.println("Статус-код ожидается не 5хх, фактический: " + response.statusCode());
        System.out.println("Тело ответа ожидается: объект с данными, фактическое: " + response.getBody().asString());
        System.out.println("======================================");
    }
}
