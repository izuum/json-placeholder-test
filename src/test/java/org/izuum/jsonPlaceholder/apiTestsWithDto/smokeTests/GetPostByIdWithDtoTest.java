package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-002: Получение конкретного post по id")
public class GetPostByIdWithDtoTest {

    @Test
    @DisplayName("TC-002: Проверка, что статус-код 200, является объектом, id=1, обязательные поля присутствуют")
    public void getPostByIdWithDto(){
        Response response = getResponse("/posts/1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString();
        assertTrue(body.startsWith("{"), "Ответ должен быть объектом");

        Post post = response.as(Post.class);
        assertNotNull(post);
        assertAll(
                () -> assertEquals(1, post.getId(), "Id не равен 1"),
                () -> assertNotNull(post.getTitle(), "Title равен null"),
                () -> assertNotNull(post.getBody(), "Body равен null"),
                () -> assertTrue(post.getUserId() > 0, "UserId меньше 1")
        );
    }
}
