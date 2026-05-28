package org.izuum.jsonPlaceholder.apiTestsWithDto.filterQueryParamTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-032: Фильтрация user по id")
public class FilterUsersByIdWithDtoTest {

    @Test
    @DisplayName("TC-032: Проверка, что статус-код 200, массив длиной 1, id=1")
    public void filterUsersByIdWithDtoTest() {
        Response response = getResponse("/users?id=1");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<User> users = response.as(new TypeRef<List<User>>() {});
        assertFalse(users.isEmpty());

        assertEquals(1, users.size());
        assertEquals(1, users.get(0).getId());
    }
}
