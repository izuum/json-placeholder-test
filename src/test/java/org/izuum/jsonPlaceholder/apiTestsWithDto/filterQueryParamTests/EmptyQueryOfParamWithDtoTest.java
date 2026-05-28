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

@DisplayName("TC-038: Пустое значение query-параметра")
public class EmptyQueryOfParamWithDtoTest {

    @Test
    @DisplayName("TC-038: Проверка, что сервис отвечает корректно, нет 5хх, ожидаемое поведение фиксируется")
    public void checkEmptyQueryOfParamWithDtoTest() {
        Response response = getResponse("/posts?userId=");

        List<Post> posts = response.as(new TypeRef<List<Post>>() {});
        assertNotNull(posts);

        assertAll(
                () -> assertTrue(response.statusCode() < HttpStatus.SC_INTERNAL_SERVER_ERROR,
                        "Статус-код: " + response.statusCode()),
                () -> assertTrue(posts.isEmpty())
        );

        System.out.println("======TC-038======");
        System.out.println("Статус-код: " + response.statusCode());
        System.out.println("Тело ответа: " + posts);
    }
}
