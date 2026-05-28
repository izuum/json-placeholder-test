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

@DisplayName("TC-029: Фильтрация todo по userId")
public class FilterTodoByUserIdWithDtoTest {

    @Test
    @DisplayName("TC-029: Проверка, что статус-код 200, каждый todo содержит userId=1")
    public void filterTodoByUserIdWithDtoTest() {
        Response response = getResponse("/todos?userId=1");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<Todo> todos = response.as(new TypeRef<List<Todo>>() {});
        assertFalse(todos.isEmpty());

        assertAll(
                todos.stream().map(todo -> () -> {
                    int index = todos.indexOf(todo);

                    assertAll(
                            () -> assertEquals(1, todo.getUserId(),
                                    "У todo[" + index + "] поле userId не равно 1")
                    );
                })
        );
    }
}
