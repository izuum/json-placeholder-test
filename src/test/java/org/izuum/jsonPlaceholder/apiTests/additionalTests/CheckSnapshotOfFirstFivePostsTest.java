package org.izuum.jsonPlaceholder.apiTests.additionalTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-129: Snapshot-контроль первых пяти элементов")
public class CheckSnapshotOfFirstFivePostsTest {

    @Test
    public void checkSnapshotOfFirstFivePostsTest() throws IOException {
        Path snapshotPath = Paths.get("src/test/resources/snapshot/first-five-posts.json");

        for(int run = 1; run <= 3; run++){
            System.out.printf("======Прогон %d======\n", run);

            Response response = RestAssured.given()
                    .spec(requestSpecification())
                    .when()
                    .get("/posts")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().response();

            List<Map<String, Object>> allPosts = response.jsonPath().getList("$");
            List<Map<String, Object>> firstFivePosts = allPosts.subList(0, Math.min(5, allPosts.size()));

            String currentSnapshot = new ObjectMapper()
                    .writerWithDefaultPrettyPrinter().writeValueAsString(firstFivePosts);

            if(run == 1 && !Files.exists(snapshotPath)){
                Files.createDirectory(snapshotPath.getParent());
                Files.writeString(snapshotPath, currentSnapshot);
                System.out.println("Создан эталон: " + currentSnapshot);
            } else {
                String expectedSnapshot = Files.readString(snapshotPath);

                assertEquals(expectedSnapshot, currentSnapshot,
                        "Прогон: " + run + "\n" +
                        "Snapshot не совпадает с эталоном -> API изменился!");
            }
        }
        System.out.println("Все проверки завершены успешно!");
    }
}
