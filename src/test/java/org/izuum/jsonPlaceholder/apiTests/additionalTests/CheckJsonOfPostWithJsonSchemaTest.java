package org.izuum.jsonPlaceholder.apiTests.additionalTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-127: Проверка схемы JSON с помощью JSON Schema")
public class CheckJsonOfPostWithJsonSchemaTest {

    @Test
    @DisplayName("TC-127: Ответ соответствует заранее сохраненной схеме")
    public void checkJsonWithJsonSchemaTest() {
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }
}
