package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-003: Получить коллекцию comments")
public class GetCollectionsOfCommentsWithDtoTest {

    @Test
    @DisplayName("TC-003: Проверить, что статус-код 200, в ответе массив, элементы содержат postId, id, name, email, body")
    public void getCollectionsOfCommentsWithDtoTest(){
        Response response = getResponse("/comments");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        String body = response.body().asString();
        assertTrue(body.startsWith("["), "Ответ должен быть массивом");

        List<Comment> comments = response.as(new TypeRef<List<Comment>>() {});
        assertFalse(comments.isEmpty());

        assertAll(
                comments.stream()
                        .map(comment -> () -> {
                    int index = comments.indexOf(comment);

                    assertAll(
                            () -> assertTrue(comment.getPostId() > 0, "У comment[" + index + "] поле PostId меньше 1"),
                            () -> assertTrue(comment.getId() > 0, "У comment[" + index + "] поле  Id меньше 1"),
                            () -> assertNotNull(comment.getName(), "У comment[" + index + "] поле Name равно null"),
                            () -> assertNotNull(comment.getEmail(), "У comment[" + index + "] поле Email равно null"),
                            () -> assertNotNull(comment.getBody(), "У comment[" + index + "] поле Body равно null")
                    );
                })
        );
    }
}
