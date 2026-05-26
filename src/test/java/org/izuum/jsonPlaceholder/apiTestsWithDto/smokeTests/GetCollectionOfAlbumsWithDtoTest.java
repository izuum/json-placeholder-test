package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Album;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-005: Получить коллекцию albums")
public class GetCollectionOfAlbumsWithDtoTest {

    @Test
    @DisplayName("TC-005: Проверить, что статус-код 200, в ответе массив, элементы содерджат userId, id, title")
    public void getCollectionOfAlbumsWithDtoTest(){
        Response response = getResponse("/albums");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString().trim();
        assertTrue(body.startsWith("["), "Ответ должен быть массивом");

        List<Album> albums = response.as(new TypeRef<List<Album>>() {});
        assertFalse(albums.isEmpty());

        assertAll(
                albums.stream().map(album -> () -> {
                    int index = albums.indexOf(album);

                    assertAll(
                            () -> assertTrue(album.getUserId() > 0, "У album[" + index + "] UserId меньше 1"),
                            () -> assertTrue(album.getId() > 0, "У album[" + index + "] Id меньше 1"),
                            () -> assertNotNull(album.getTitle(), "У album[" + index + "] Title равен null")
                    );
                })
        );
    }
}
