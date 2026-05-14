package org.izuum.jsonPlaceholder.apiTests.fullReplaceData;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-079: Полная замена comment")
public class FullReplaceCommentTest {

    @Test
    @DisplayName("TC-078: Статус-код 200")
    public void checkThatStatusCodeIsOk(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("postId", 10);
        requestBody.put("name", "John Slow");
        requestBody.put("email", "test@john.slow");
        requestBody.put("body", "test body for john slow");

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .put("/comments/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-079: Поля из payload возвращаются")
    public void checkThatPayloadIsOk(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("postId", 10);
        requestBody.put("name", "John Slow");
        requestBody.put("email", "test@JohnSlow");
        requestBody.put("body", "test body for john slow");

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .put("/comments/1")
                .then()
                .body("postId", equalTo(requestBody.get("postId")))
                .body("name", equalTo(requestBody.get("name")))
                .body("email", equalTo(requestBody.get("email")))
                .body("body", equalTo(requestBody.get("body")));
    }
}
