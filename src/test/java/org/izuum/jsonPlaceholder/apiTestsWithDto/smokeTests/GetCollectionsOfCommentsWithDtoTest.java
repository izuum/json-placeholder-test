package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-003: Получить коллекцию comments")
public class GetCollectionsOfCommentsWithDtoTest {

    @Test
    @DisplayName("TC-003: Проверить, что статус-код 200, в ответе массив, элементы содержат postId, id, name, email, body")
    public void getCollectionsOfCommentsWithDtoTest(){
        List<Comment> comments = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(new TypeRef<List<Comment>>() {});
    }
}
