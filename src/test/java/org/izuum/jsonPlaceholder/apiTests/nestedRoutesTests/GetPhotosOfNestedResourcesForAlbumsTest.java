package org.izuum.jsonPlaceholder.apiTests.nestedRoutesTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCodeGet;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-040: Получить photos вложенного ресурса для album")
public class GetPhotosOfNestedResourcesForAlbumsTest {

    @Test
    @DisplayName("TC-040: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCodeGet("/albums/1/photos", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-040: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums/1/photos")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-040: Каждый элемент имеет albumId=1")
    public void checkThatEveryElementsHasAlbumIdIsOne(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums/1/photos")
                .then()
                .body("albumId", everyItem(equalTo(1)));
    }
}
