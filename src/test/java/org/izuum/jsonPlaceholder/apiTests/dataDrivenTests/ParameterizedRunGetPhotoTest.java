package org.izuum.jsonPlaceholder.apiTests.dataDrivenTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-118: Параметризованный запрос GET для /photos по id=1..10")
public class ParameterizedRunGetPhotoTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("TC-118: Проверка что каждый запрос по id возвращает валидный photo")
    public void parameterizedGetPhotoTest(int photoId){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos/" + photoId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(photoId))
                .body("albumId", notNullValue())
                .body("title", notNullValue())
                .body("url", notNullValue())
                .body("thumbnailUrl", notNullValue());
    }
}
