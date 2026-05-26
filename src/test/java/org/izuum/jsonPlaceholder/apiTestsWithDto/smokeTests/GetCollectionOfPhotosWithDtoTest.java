package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Photo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-007: Получить коллекцию photos")
public class GetCollectionOfPhotosWithDtoTest {

    @Test
    @DisplayName("TC-007: Проверить, что статус-код 200, в ответе массив, элементы содержат albumId, id, title, url, thumbnailUrl")
    public void getCollectionOfPhotosWithDtoTest() {
        Response response = getResponse("/photos");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString();
        assertTrue(body.startsWith("["), "В ответе должен быть массив");

        List<Photo> photos = response.as(new TypeRef<List<Photo>>() {});
        assertFalse(photos.isEmpty());

        assertAll(
                photos.stream().map(photo -> () -> {
                    int index = photos.indexOf(photo);

                    assertAll(
                            () -> assertTrue(photo.getAlbumId() > 0, "У photo[" + index + "] поле albumId меньше 1"),
                            () -> assertTrue(photo.getId() > 0, "У photo[" + index + "] поле id меньше 1"),
                            () -> assertNotNull(photo.getTitle(), "У photo[" + index + "] поле title равно null"),
                            () -> assertNotNull(photo.getUrl(), "У photo[" + index + "] поле url равно null"),
                            () -> assertNotNull(photo.getThumbnailUrl(), "У photo[" + index + "] поле thumbnailUrl равно null")
                    );
                })
        );
    }
}
