package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.RestAssured;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-002: Получение конкретного post по id")
public class GetPostByIdWithDtoTest {

    @Test
    @DisplayName("TC-002: Проверка, что статус-код 200, является объектом, id=1, обязательные поля присутствуют")
    public void getPostByIdWithDto(){
        Post post = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Post.class);

        assertNotNull(post);
        assertEquals(1, post.getId());
        assertNotNull(post.getTitle());
        assertNotNull(post.getBody());
        assertTrue(post.getUserId() > 0);
    }
}
