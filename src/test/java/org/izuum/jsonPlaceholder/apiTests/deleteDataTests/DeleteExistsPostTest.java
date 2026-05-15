package org.izuum.jsonPlaceholder.apiTests.deleteDataTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-089: Удаление существующего post")
public class DeleteExistsPostTest {

    @Test
    @DisplayName("TC-089: Статус-код 200 либо 204(по фактическому контакту)")
    public void checkStatusCodeOfDeletePost(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(anyOf(is(HttpStatus.SC_OK), is(HttpStatus.SC_NO_CONTENT)));
    }

    @Test
    @DisplayName("TC-089: Тело либо пустое, либо {}")
    public void checkBodyOfRequest(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .delete("/posts/1")
                .then()
                .body(anyOf(equalTo(emptyString()), equalTo("{}")));
    }
}
