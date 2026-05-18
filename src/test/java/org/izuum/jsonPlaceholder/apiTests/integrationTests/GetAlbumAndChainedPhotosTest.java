package org.izuum.jsonPlaceholder.apiTests.integrationTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-099: Сравнение полученного album и photos относящихся к album")
public class GetAlbumAndChainedPhotosTest {

    @Test
    @DisplayName("TC-099: Проверка что album.id совпадает со всеми albumId во всех photos")
    public void getAlbumAndChainedPhotos() {
        Response albumResponse = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("albums/1");

        int albumId = albumResponse.jsonPath().getInt("id");

        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("albums/" + albumId + "/photos")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("albumId", everyItem(equalTo(albumId)))
                .body("$", not(empty()));
    }
}
