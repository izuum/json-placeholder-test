package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Photo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-008: Получить photo по id")
public class GetPhotoByIdWithDtoTest {

    @Test
    @DisplayName("TC-008: Проверить, что статус-код 200, в ответе объект, id=1, url и thumbnailUrl - строки")
    public void getPhotoByIdWithDtoTest() {
        Response response = getResponse("/photos/1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString();
        assertTrue(body.startsWith("{"), "В ответе должен быть объект");

        Photo photo = response.as(Photo.class);
        assertNotNull(photo);

        assertAll(
                () -> assertEquals(1, photo.getId(), "Id не равен 1"),
                () -> assertInstanceOf(String.class, photo.getUrl(), "Url не является строкой"),
                () -> assertNotNull(photo.getUrl(), "Url равен null"),
                () -> assertInstanceOf(String.class, photo.getThumbnailUrl(), "ThumbnailUrl не является строкой"),
                () -> assertNotNull(photo.getThumbnailUrl(), "ThumbnailUrl равен null")
        );
    }
}
