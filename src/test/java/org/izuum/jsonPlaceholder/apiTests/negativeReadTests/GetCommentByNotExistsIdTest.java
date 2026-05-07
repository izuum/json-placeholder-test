package org.izuum.jsonPlaceholder.apiTests.negativeReadTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-053: Запрос comment с несуществующим id")
public class GetCommentByNotExistsIdTest {

    @Test
    @DisplayName("TC-053: Статус-код не 5хх")
    public void checkExpectedNotServerError(){
        Response response = getResponse("/comments/9999");

        assertTrue(response.statusCode() < 500);

        System.out.println("TC-053: ======Фиксация контракта======");
        System.out.println("Запрос: GET /comments/9999");
        System.out.println("Статус-код ожидается не 5хх, фактический: " + response.statusCode());
        System.out.println("Тело ответа ожидается {} (пустой объект), фактическое: " + response.getBody().asString());
        System.out.println("======================================");
    }
}
