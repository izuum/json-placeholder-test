package org.izuum.jsonPlaceholder.apiTestsWithDto.filterQueryParamTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("TC-033: Фильтрация posts по id")
public class FilterPostsByIdWithDtoTest {

    @Test
    @DisplayName("TC-033: Проверка, что статус-код 200, массив длиной 1, id=1")
    public void filterPostsByIdWithDtoTest() {
        Response response = getResponse("/posts?id=1");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<Post> posts = response.as(new TypeRef<List<Post>>() {});
        assertFalse(posts.isEmpty());

        assertEquals(1, posts.size());
        assertEquals(1, posts.get(0).getId());
    }
}
