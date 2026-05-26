package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-004: Получить comment по id")
public class GetCommentByIdWithDtoTest {

    @Test
    @DisplayName("TC-004: Проверить, что статус-код 200, id=1, email представлен строкой")
    public void getCommentByIdWithDto() {
        Response response = getResponse("/comments/1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString();
        Comment comment = response.as(Comment.class);
        assertNotNull(comment);

        assertAll(
                () -> assertTrue(body.startsWith("{"), "Ответ должен быть объектом"),
                () -> assertEquals(1, comment.getId(), "Id не равен 1"),
                () -> assertInstanceOf(String.class, comment.getEmail(), "Email не является строкой"),
                () -> assertTrue(comment.getEmail().contains("@"), "Email должен содержать '@'")
        );
    }
}
