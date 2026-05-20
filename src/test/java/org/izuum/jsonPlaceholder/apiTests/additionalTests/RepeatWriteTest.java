package org.izuum.jsonPlaceholder.apiTests.additionalTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-126: Повторный прогон write-тестов для проверки отсутствия деградации ответа")
public class RepeatWriteTest {

    @Test
    @DisplayName("TC-126: Прогон post-теста")
    public void repeatPostTest(){
        Map<String, Object> postMap  = new HashMap<>();
        postMap.put("userId", 54);
        postMap.put("title", "some title");
        postMap.put("body", "some body");

        for(int run = 1; run <= 3; run++){
            try {
                RestAssured.given()
                        .spec(requestSpecification())
                        .body(postMap)
                        .when()
                        .post("/posts")
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .body("id", notNullValue())
                        .body("title", equalTo(postMap.get("title")))
                        .body("body", equalTo(postMap.get("body")))
                        .body("userId", equalTo(postMap.get("userId")));

                System.out.println("Прогон post-теста №" + run + " успешен!");
            } catch (AssertionError e) {
                System.out.println("Прогон post-теста №" + run + " завершился с ошибкой!");
                Assertions.fail("Тест провален на прогоне №" + run + ". Причина: " + e.getMessage());
            }
        }
        System.out.println();
    }

    @Test
    @DisplayName("TC-126: Прогон put-теста")
    public void repeatPutTest() {
        Map<String, Object> putMap = new HashMap<>();
        putMap.put("userId", 10);
        putMap.put("title", "some title");
        putMap.put("body", "some body");

        for(int run = 1; run <= 3; run++){
            try {
                RestAssured.given()
                        .spec(requestSpecification())
                        .body(putMap)
                        .when()
                        .put("/posts/1")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .body("id", notNullValue())
                        .body("userId", equalTo(putMap.get("userId")))
                        .body("title", equalTo(putMap.get("title")))
                        .body("body", equalTo(putMap.get("body")));

                System.out.println("Прогон put-теста №" + run + " успешен!");
            } catch (AssertionError e) {
                System.out.println("Прогон put-теста №" + run + " завершился с ошибкой!");
                Assertions.fail("Тест провален на прогоне №" + run + ". Причина: " + e.getMessage());
            }
        }
        System.out.println();
    }

    @Test
    @DisplayName("TC-126: Прогон patch-теста")
    public void repeatPatchTest() {
        Map<String, Object> patchMap = new HashMap<>();
        patchMap.put("userId", 10);
        patchMap.put("title", "some title");

        for(int run = 1; run <= 3; run++){
            try {
                RestAssured.given()
                        .spec(requestSpecification())
                        .body(patchMap)
                        .when()
                        .patch("/posts/1")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .body("id", notNullValue())
                        .body("userId", equalTo(patchMap.get("userId")))
                        .body("title", equalTo(patchMap.get("title")))
                        .body("body", notNullValue());
                System.out.println("Прогон patch-теста №" + run + " успешен!");
            } catch (AssertionError e) {
                System.out.println("Прогон patch-теста №" + run + " завершился с ошибкой!");
                Assertions.fail("Тест провален на прогоне №" + run + ". Причина: " + e.getMessage());
            }
        }
        System.out.println();
    }

    @Test
    @DisplayName("TC-126: Прогон delete-теста")
    public void repeatDeleteTest() {
        for(int run = 1; run <= 3; run++){
            try {
                RestAssured.given()
                        .spec(requestSpecification())
                        .when()
                        .delete("/posts/1")
                        .then()
                        .statusCode(HttpStatus.SC_OK);
                System.out.println("Прогон delete-теста №" + run + " успешен!");
            } catch (AssertionError e) {
                System.out.println("Прогон delete-теста №" + run + " завершился с ошибкой!");
                Assertions.fail("Тест провален на прогоне №" + run + ". Причина: " + e.getMessage());
            }
        }
        System.out.println();
    }
}
