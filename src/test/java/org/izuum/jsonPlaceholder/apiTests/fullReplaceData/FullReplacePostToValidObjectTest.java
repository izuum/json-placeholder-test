package org.izuum.jsonPlaceholder.apiTests.fullReplaceData;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-073: Полная замена post валидным объектом")
public class FullReplacePostToValidObjectTest {

    @Test
    @DisplayName("TC-073: Статус-код 200")
    public void getRequestCheckStatusCode(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("title", "some title for testing");
        requestBody.put("body", "some body for testing");

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .put("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-073: Ответ содержит отправленные поля и id=1")
    public void checkThatRequestHasSentFieldsAndIdIsOne(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("title", "some title for testing");
        requestBody.put("body", "some body for testing");

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .put("/posts/1")
                .then()
                .body("id", equalTo(1))
                .body("userId", equalTo(requestBody.get("userId")))
                .body("title", equalTo(requestBody.get("title")))
                .body("body", equalTo(requestBody.get("body")));
    }
}
