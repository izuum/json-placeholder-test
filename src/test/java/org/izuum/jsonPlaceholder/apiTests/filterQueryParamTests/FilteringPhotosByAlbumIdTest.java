package org.izuum.jsonPlaceholder.apiTests.filterQueryParamTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-028: Фильтрация photos по albumId")
public class FilteringPhotosByAlbumIdTest {

    @Test
    @DisplayName("TC-028: Статус-код 200")
    public void getRequestCheckStatusCode(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos?albumId=1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-028: Каждый photo содержит albumId = 1")
    public void checkThaTAllElementsOfPhotosHasSpecificAlbumId(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos?albumId=1")
                .then()
                .body("albumId", everyItem(equalTo(1)));
    }
}
