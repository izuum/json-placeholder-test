package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Album;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-006: Получить album по id")
public class GetAlbumByIdWithDtoTest {

    @Test
    @DisplayName("TC-006: Проверить, что статус-код 200, в ответе объект, title не пустой")
    public void getAlbumByIdWithDtoTest() {
        Response response = getResponse("/albums/1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString().trim();
        Album album = response.as(Album.class);
        assertNotNull(album);

        assertAll(
                () -> assertTrue(body.startsWith("{"), "В ответе должен быть объект"),
                () -> assertEquals(1, album.getId(), "Id не равен 1"),
                () -> assertFalse(album.getTitle().isEmpty(), "Title пустой")
        );
    }
}
