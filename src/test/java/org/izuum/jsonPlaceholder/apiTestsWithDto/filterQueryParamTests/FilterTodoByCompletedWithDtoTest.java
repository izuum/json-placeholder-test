package org.izuum.jsonPlaceholder.apiTestsWithDto.filterQueryParamTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Фильтрация todo по completed")
public class FilterTodoByCompletedWithDtoTest {

    @Test
    @DisplayName("TC-030: Проверка, что статус-код 200, каждый todo.completed=true")
    public void filterTodoByCompletedTrueWithDtoTest() {
        Response response = getResponse("/todos?completed=true");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<Todo> todos = response.as(new TypeRef<List<Todo>>() {
        });
        assertFalse(todos.isEmpty());

        assertAll(
                todos.stream().map(todo -> () -> {
                    int index = todos.indexOf(todo);

                    assertAll(
                            () -> assertTrue(todo.getCompleted(), "У todo[" + index + "] поле completed не true")
                    );
                })
        );
    }

    @Test
    @DisplayName("TC-031: Проверка, что статус-код 200, каждый todo.completed=false")
    public void filterTodoByCompletedFalseWithDtoTest() {
        Response response = getResponse("/todos?completed=false");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<Todo> todos = response.as(new TypeRef<List<Todo>>() {});
        assertFalse(todos.isEmpty());

        assertAll(
                todos.stream().map(todo -> () -> {
                    int index = todos.indexOf(todo);

                    assertAll(
                            () -> assertFalse(todo.getCompleted(),
                                    "У todo[" + index +"] поле completed не равно false")
                    );
                })
        );
    }

}
