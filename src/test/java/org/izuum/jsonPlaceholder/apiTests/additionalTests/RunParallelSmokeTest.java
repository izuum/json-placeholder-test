package org.izuum.jsonPlaceholder.apiTests.additionalTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-125: Параллельный запуск smoke-тестов")
public class RunParallelSmokeTest {

    @Test
    public void parallelRunSmokeTest(){
        List<String> endpoints = List.of("/posts", "/comments", "/albums", "/photos", "/todos", "/users",
                "/posts/1", "/comments/1", "/albums/1", "/photos/1", "/todos/1", "/users/1");

        Map<String, Integer> results = endpoints.parallelStream()
                .collect(Collectors.toMap(
                        endpoint -> endpoint,
                        endpoint -> RestAssured.given()
                                .spec(requestSpecification())
                                .when()
                                .get(endpoint)
                                .statusCode()
                ));
        System.out.println("======Результаты параллельного запуска======");
        results.forEach((endpoint, statusCode) -> {
            System.out.println(endpoint + " -> "  + statusCode);

            assertEquals(HttpStatus.SC_OK, statusCode);
        });

        long successCount = results.values().stream()
                .filter(status -> status == HttpStatus.SC_OK)
                .count();
        System.out.println("===========================");
        if(successCount == endpoints.size()){
            System.out.println("Успшено: " + successCount + "/" + endpoints.size());
            System.out.println("Все smoke-тесты прошли успешно");
        } else {
            System.out.println("Успшено: " + successCount + "/" + endpoints.size());
            System.out.println("Некоторые smoke-тесты завершились с ошибкой");
        }
    }
}
