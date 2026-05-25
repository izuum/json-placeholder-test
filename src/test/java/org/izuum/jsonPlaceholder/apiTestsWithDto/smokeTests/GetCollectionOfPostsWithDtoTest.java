package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("TC-001: Получение коллекции posts")
public class GetCollectionOfPostsWithDtoTest {

    @Test
    @DisplayName("TC-001: Проверка, что статус-код 200, массив не пустой, длина > 0, первый элемент имеет поля id, title, body, userId")
    public void getCollectionOfPostsWithDto(){
        Response response = getResponse("/posts");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        String body = response.getBody().asString().trim();
        assertTrue(body.startsWith("["), "Ответ должен быть массивом");

        List<Post> posts = response.as(new TypeRef<List<Post>>() {});
        assertFalse(posts.isEmpty());
        assertTrue(posts.size() >= 1);

        Post firstPost = posts.get(0);
        assertNotNull(firstPost);
        assertTrue(firstPost.getId() > 0);
        assertNotNull(firstPost.getTitle());
        assertNotNull(firstPost.getBody());
        assertTrue(firstPost.getUserId() > 0);
    }
}
