package org.izuum.jsonPlaceholder.apiTestsWithDto.filterQueryParamTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-026: Фильтрация comments по postId")
public class FilterCommentsByPostIdWithDtoTest {

    @Test
    @DisplayName("TC-026: Проверка, что статус-код 200, все элементы имеют postId=1")
    public void checkFilterCommentsByPostIdWithDto() {
        Response response = getResponse("/comments?postId=1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<Comment> comments = response.as(new TypeRef<List<Comment>>() {});
        assertFalse(comments.isEmpty());

        assertAll(
                comments.stream().map(comment -> () -> {
                    int index = comments.indexOf(comment);

                    assertAll(
                            () -> assertEquals(1, comment.getPostId(), "У comment[" + index + "] поле postId не равно 1")
                    );
                })
        );
    }
}
