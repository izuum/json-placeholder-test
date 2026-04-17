package org.izuum.jsonPlaceholder.apiTests.structureAndTypeTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("Проверка типов полей на всех эндпоинтах")
public class CheckTypeOfFieldsTest {

    @Test
    @DisplayName("TC-013: Проверка типов полей post")
    public void checkTypeOfFieldsOfPost(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .body("id", instanceOf(Integer.class))
                .body("userId", instanceOf(Integer.class))
                .body("title", instanceOf(String.class))
                .body("body", instanceOf(String.class));
    }

    @Test
    @DisplayName("TC-014: Проверка типов полей comment")
    public void checkTypeOfFieldsOfComment(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/comments/1")
                .then()
                .body("postId", instanceOf(Integer.class))
                .body("id", instanceOf(Integer.class))
                .body("name", instanceOf(String.class))
                .body("email", instanceOf(String.class))
                .body("body", instanceOf(String.class));
    }

    @Test
    @DisplayName("TC-015: Проверка типов полей album")
    public void checkTypeOfFieldsOfAlbum(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/albums/1")
                .then()
                .body("userId", instanceOf(Integer.class))
                .body("id", instanceOf(Integer.class))
                .body("title", instanceOf(String.class));
    }

    @Test
    @DisplayName("TC-016: Проверка типов полей photo")
    public void checkTypeOfFieldsOfPhoto(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/photos/1")
                .then()
                .body("albumId", instanceOf(Integer.class))
                .body("id", instanceOf(Integer.class))
                .body("title", instanceOf(String.class))
                .body("url", instanceOf(String.class))
                .body("thumbnailUrl", instanceOf(String.class));
    }

    @Test
    @DisplayName("TC-017: Проверка типов полей todo")
    public void checkTypeOfFieldsOfToDo(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos/1")
                .then()
                .body("userId", instanceOf(Integer.class))
                .body("id", instanceOf(Integer.class))
                .body("title", instanceOf(String.class))
                .body("completed", instanceOf(Boolean.class));
    }

    @Test
    @DisplayName("TC-018: Проверка типов верхнего уровня user")
    public void checkTypeOfFieldsTopLevelOfUser(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .body("id", instanceOf(Integer.class))
                .body("name", instanceOf(String.class))
                .body("username", instanceOf(String.class))
                .body("email", instanceOf(String.class))
                .body("phone", instanceOf(String.class))
                .body("website", instanceOf(String.class));
    }
}
