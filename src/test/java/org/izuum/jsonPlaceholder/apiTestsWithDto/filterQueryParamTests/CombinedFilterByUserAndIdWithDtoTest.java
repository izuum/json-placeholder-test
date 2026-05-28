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

@DisplayName("TC-034: Фильтрация по userId и id")
public class CombinedFilterByUserAndIdWithDtoTest {

    @Test
    @DisplayName("TC-034: Проверка, что статус-код 200, все элементы удовлетворяют обоим условиям")
    public void filterPostsByUserIdAndIdWithDtoTest(){
        Response response = getResponse("/posts?userId=1&id=1");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<Post> posts = response.as(new TypeRef<List<Post>>() {});
        assertFalse(posts.isEmpty());

        assertAll(
                posts.stream().map(post -> () -> {
                    int index = posts.indexOf(post);

                    assertAll(
                            () -> assertEquals(1, post.getUserId(),
                                    "У post[" + index + "] поле userId не равен 1"),
                            () -> assertEquals(1, post.getId(),
                                    "У post[" + index + "] поле id не равно 1")
                    );
                })
        );
    }
}
