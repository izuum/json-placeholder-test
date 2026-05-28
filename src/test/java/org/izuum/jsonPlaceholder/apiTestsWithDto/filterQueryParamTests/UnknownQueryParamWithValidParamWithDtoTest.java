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

@DisplayName("TC-037: Фильтрация с неизвестным query-параметром вместе с валидным")
public class UnknownQueryParamWithValidParamWithDtoTest {

    @Test
    @DisplayName("TC-037: Проверка, что сервис отвечает корректно, нет 5хх, поведение по UnknownParam задокументировано")
    public void filterPostsByUnknownParam() {
        Response response = getResponse("/posts?userId=1&unknownParam=x");

        List<Post> posts = response.as(new TypeRef<List<Post>>() {});
        assertNotNull(posts);

        assertAll(
                () -> assertTrue(response.statusCode() < HttpStatus.SC_INTERNAL_SERVER_ERROR,
                        "Статус-код: " + response.statusCode()),
                () -> assertFalse(posts.isEmpty(), "Массив пустой")
        );

        System.out.println("======TC-037======");
        System.out.println("Статус-код: " + response.statusCode());
        System.out.println("Тело ответа: " + posts);
    }
}
