package org.izuum.jsonPlaceholder.apiTests.additionalTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-128: Проверка схемы вложенных объектов в user с помощью JSON Schema")
public class CheckJsonOfUserWithJsonSchemaTest {

    @Test
    @DisplayName("TC-128: Проверка что все поля и объекты соответствуют схеме")
    public void checkJsonOfUserWithJsonSchemaTest() {
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }
}
