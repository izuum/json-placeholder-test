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
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-035: Фильтрация posts по несуществующему userId")
public class FilterPostsByNotExistingValueWithDtoTest {

    @Test
    @DisplayName("TC-035: Проверка, что статус-код 200, пустой массив")
    public void filterPostsByNotExistingValueWithDtoTest(){
        Response response = getResponse("/posts?userId=9999");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<Post> posts = response.as(new TypeRef<List<Post>>() {});
        assertTrue(posts.isEmpty());
    }
}
