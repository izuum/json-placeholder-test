package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-010: Получить todo по id")
public class GetTodoByIdWithDtoTest {

    @Test
    @DisplayName("TC-010: Проверить, что статус-код 200, в ответе объект, id=1, completed имеет тип boolean")
    public void getTodoByIdWithDtoTest() {
        Response response = getResponse("/todos/1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString().trim();
        assertTrue(body.startsWith("{"), "В ответе должен быть объект");

        Todo todo = response.as(new TypeRef<Todo>() {});
        assertNotNull(todo);

        assertAll(
                () -> assertEquals(1, todo.getId(), "Id не равен 1"),
                () -> assertNotNull(todo.getCompleted(), "Todo равен null"),
                () -> assertInstanceOf(Boolean.class, todo.getCompleted())
        );
    }
}
