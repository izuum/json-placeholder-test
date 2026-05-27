package org.izuum.jsonPlaceholder.apiTestsWithDto.filterQueryParamTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Album;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-027: Фильтрация albums по userId")
public class FilterAlbumsByUserIdWithDtoTest {

    @Test
    @DisplayName("TC-027: Проверка, что статус-код 200, каждый элемент album принадлежит userId=1")
    public void checkFilterAlbumsByUserIdWithDtoTest() {
        Response response = getResponse("/albums?userId=1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<Album> albums = response.as(new TypeRef<List<Album>>() {});
        assertFalse(albums.isEmpty());

        assertAll(
                albums.stream().map(album -> () -> {
                    int index = albums.indexOf(album);

                    assertAll(
                            () -> assertEquals(1, album.getUserId(), "У album[" + index + "] поле userId не равно 1")
                    );
                })
        );
    }
}
