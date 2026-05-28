package org.izuum.jsonPlaceholder.apiTestsWithDto.filterQueryParamTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-036: Некорректное значение boolean-фильтра")
public class IncorrectValueOfBooleanFilterWithDtoTest {

    @Test
    @DisplayName("TC-036: Проверка стабильности ответа, сервис не падает, пустой массив фиксируется в каждом ответе")
    public void incorrectValueOfBooleanFilterWithDtoTest(){
        Response firstResponse = getResponse("/todos?completed=invalid");
        Response secondResponse = getResponse("/todos?completed=invalid");
        Response thirdResponse = getResponse("/todos?completed=invalid");

        List<Post> firstPosts = firstResponse.as(new TypeRef<List<Post>>() {});
        List<Post> secondPosts = secondResponse.as(new TypeRef<List<Post>>() {});
        List<Post> thirdPosts = thirdResponse.as(new TypeRef<List<Post>>() {});

        assertAll(
                () -> assertEquals(HttpStatus.SC_OK, firstResponse.statusCode()),
                () -> assertEquals(firstResponse.statusCode(), secondResponse.statusCode()),
                () -> assertEquals(secondResponse.statusCode(), thirdResponse.statusCode()),
                () -> assertTrue(firstResponse.statusCode() < HttpStatus.SC_INTERNAL_SERVER_ERROR),
                () -> assertTrue(secondResponse.statusCode() < HttpStatus.SC_INTERNAL_SERVER_ERROR),
                () -> assertTrue(thirdResponse.statusCode() < HttpStatus.SC_INTERNAL_SERVER_ERROR),
                () -> assertTrue(firstPosts.isEmpty()),
                () -> assertTrue(secondPosts.isEmpty()),
                () -> assertTrue(thirdPosts.isEmpty())
        );
    }
}
