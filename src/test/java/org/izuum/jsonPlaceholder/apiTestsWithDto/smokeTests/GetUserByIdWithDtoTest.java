package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-012: Получить user по id")
public class GetUserByIdWithDtoTest {

    @Test
    @DisplayName("TC-012: Проверить, что статус-код 200, в ответе обьект, вложенные  address и company присутствуют")
    public void getUserByIdWithDtoTest() {
        Response response = getResponse("/users/1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString().trim();
        assertTrue(body.startsWith("{"), "В ответе должен быть объект");

        User user = response.as(new TypeRef<User>() {});
        assertNotNull(user);

        assertAll(
                () -> assertNotNull(user.getAddress(), "Address равен null"),
                () -> assertNotNull(user.getAddress().getCity(), "Поле city, вложенное в address отсутствует"),
                () -> assertNotNull(user.getCompany(), "Company равно null"),
                () -> assertNotNull(user.getCompany().getName(), "Поле name, вложенное в company отсутствует")
        );
    }
}
