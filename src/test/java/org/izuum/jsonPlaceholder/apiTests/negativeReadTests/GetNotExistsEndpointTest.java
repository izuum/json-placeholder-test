package org.izuum.jsonPlaceholder.apiTests.negativeReadTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-060: Запрос несуществующего эндпоинта")
public class GetNotExistsEndpointTest {

    @Test
    @DisplayName("TC-060: Статус-код ожидается 404")
    public void checkExpectedNotFound(){
        Response response = getResponse("/unknown-resource");

        assertTrue(response.statusCode() < 500);
        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
    }
}
