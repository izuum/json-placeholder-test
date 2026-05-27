package org.izuum.jsonPlaceholder.apiTestsWithDto.structureAndTypeTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка типов полей на всех эндпоинтах")
public class CheckTypeOfFieldsTests {

    @Test
    @DisplayName("TC-013: Проверка типов полей post")
    public void checkTypeOfFieldsPost() {
        Response response = getResponse("/posts/1");

        assertAll(
                () -> assertEquals(HttpStatus.SC_OK, response.getStatusCode()),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("id")),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("userId")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("title")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("body"))
        );
    }

    @Test
    @DisplayName("TC-014: Проверка типов полей comment")
    public void checkTypeOfFieldsComment() {
        Response response = getResponse("/comments/1");

        assertAll(
                () -> assertEquals(HttpStatus.SC_OK, response.getStatusCode()),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("postId")),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("id")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("name")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("email")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("body"))
        );
    }

    @Test
    @DisplayName("TC-015: Проверка типов полей album")
    public void checkTypeOfFieldsAlbum() {
        Response response = getResponse("/albums/1");

        assertAll(
                () -> assertEquals(HttpStatus.SC_OK, response.statusCode()),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("userId")),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("id")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("title"))
        );
    }

    @Test
    @DisplayName("TC-016: Проверка типов полей photo")
    public void checkTypeOfFieldsPhoto() {
        Response response = getResponse("/photos/1");

        assertAll(
                () -> assertEquals(HttpStatus.SC_OK, response.statusCode()),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("albumId")),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("id")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("title")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("url")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("thumbnailUrl"))
        );
    }

    @Test
    @DisplayName("TC-017: Проверка типов полей todo")
    public void checkTypeOfFieldsTodo() {
        Response response = getResponse("/todos/1");

        assertAll(
                () -> assertEquals(HttpStatus.SC_OK, response.statusCode()),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("userId")),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("id")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("title")),
                () -> assertInstanceOf(Boolean.class, response.jsonPath().get("completed"))
        );
    }

    @Test
    @DisplayName("TC-018: Проверка типов полей верхнего уровня user")
    public void checkTypeOfFieldsUser() {
        Response response = getResponse("/users/1");

        assertAll(
                () -> assertEquals(HttpStatus.SC_OK, response.statusCode()),
                () -> assertInstanceOf(Integer.class, response.jsonPath().get("id")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("name")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("username")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("email")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("phone")),
                () -> assertInstanceOf(String.class, response.jsonPath().get("website"))
        );
    }
}
