package org.izuum.jsonPlaceholder.apiTests.negativeReadTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-057: Запрос User с несуществующим id")
public class GetUserByNotExistsIdTest {

    @Test
    @DisplayName("TC-057: Статус-код не 5хх")
    public void checkExpectedNotServerError(){
        Response response = getResponse("/users/9999");

        assertTrue(response.statusCode() < 500);

        System.out.println("TC-057: ======Фиксация контракта======");
        System.out.println("Запрос: GET /users/9999");
        System.out.println("Статус-код ожидается не 5хх, фактический: " + response.statusCode());
        System.out.println("Тело ответа ожидается {} (пустое тело), фактическое: " + response.getBody().asString());
        System.out.println("======================================");
    }
}
