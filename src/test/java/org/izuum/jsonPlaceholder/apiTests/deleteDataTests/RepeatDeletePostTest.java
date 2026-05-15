package org.izuum.jsonPlaceholder.apiTests.deleteDataTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.deleteResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-090: Повторное удаление одного и того же ресурса")
public class RepeatDeletePostTest {

    @Test
    @DisplayName("TC-090: Статус-код не 5хх, фиксация идемпотентности")
    public void checkStatusCodeOfRepeatDeletePost() {
        Response response = deleteResource("/posts/1");
        Response repeatedResponse = deleteResource("/posts/1");

        assertTrue(response.getStatusCode() < HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertTrue(repeatedResponse.getStatusCode() < HttpStatus.SC_INTERNAL_SERVER_ERROR);

        assertEquals(response.getBody().asString(), repeatedResponse.getBody().asString());

        System.out.println("======Фиксация идемпотентности======");
        System.out.println("Запрос на удаление ресурса отправлен дважды");
        System.out.println("Статус-код ответа первого запроса: " + response.getStatusCode());
        System.out.println("Статус-код ответа второго запроса: " + repeatedResponse.getStatusCode());
        System.out.println("Тело ответа первого запроса: " + response.getBody().asString());
        System.out.println("Тело ответа второго запроса: " + repeatedResponse.getBody().asString());
        System.out.println("====================================");
    }
}
