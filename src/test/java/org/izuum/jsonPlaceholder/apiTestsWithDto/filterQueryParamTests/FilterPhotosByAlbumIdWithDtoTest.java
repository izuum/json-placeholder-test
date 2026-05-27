package org.izuum.jsonPlaceholder.apiTestsWithDto.filterQueryParamTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Photo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-028: Фильтрация photos по albumId")
public class FilterPhotosByAlbumIdWithDtoTest {

    @Test
    @DisplayName("TC-028: Проверка, что статус-код 200, каждый photo содержит albumId=1")
    public void checkFilterPhotosByAlbumIdWithDtoTest() {
        Response response = getResponse("/photos?albumId=1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<Photo> photos = response.as(new TypeRef<List<Photo>>() {});
        assertFalse(photos.isEmpty());

        assertAll(
                photos.stream().map(photo -> () -> {
                    int index = photos.indexOf(photo);

                    assertAll(
                            () -> assertEquals(1, photo.getAlbumId(), "У photo[" + index + "] поле albumId не равно 1")
                    );
                })
        );
    }
}
