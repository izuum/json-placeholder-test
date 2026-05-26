package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-009: Получить коллекцию todo")
public class GetCollectionOfToDoWithDtoTest {

    @Test
    @DisplayName("TC-009: Проверить, что статус-код 200, в ответе массив, элементы содержат userId, id, title, completed")
    public void getCollectionOfToDoWithDtoTest(){
        Response response = getResponse("/todos");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString();
        assertTrue(body.startsWith("["), "В ответе должен быть массив");

        List<Todo> todos = response.as(new TypeRef<List<Todo>>() {});
        assertFalse(todos.isEmpty());

        assertAll(
                todos.stream().map(todo -> () -> {
                    int index = todos.indexOf(todo);

                    assertAll(
                            () -> assertTrue(todo.getUserId() > 0, "У todo[" + index + "] поле userId меньше 1"),
                            () -> assertTrue(todo.getId() > 0, "У todo[" + index + "] поле id меньше 1"),
                            () -> assertNotNull(todo.getTitle(), "У todo[" + index + "] поле title равно null"),
                            () -> assertNotNull(todo.getCompleted(), "У todo[" + index + "] поле completed равно null"),
                            () -> assertInstanceOf(Boolean.class, todo.getCompleted())
                    );
                })
        );
    }
}
