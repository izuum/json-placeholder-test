package org.izuum.jsonPlaceholder.apiTests.fullReplaceData;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-074: Полная замена с изменением всех значений")
public class FullReplacePostWithChangeAllValuesTest {

    @Test
    @DisplayName("TC-074: Статус-код 200")
    public void getRequestCheckStatusCode(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("userId", 999);
        requestBody.put("id", 99);
        requestBody.put("title", "SOME NEW TITLE");
        requestBody.put("body", "SOME NEW BODY");

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
    @DisplayName("TC-074: Каждое поле обновлено в response body")
    public void checkThatEveryFieldsAreUpdate(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("userId", 999);
        requestBody.put("id", 99);
        requestBody.put("title", "SOME NEW TITLE");
        requestBody.put("body", "SOME NEW BODY");

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .put("/posts/1")
                .then()
                .body("userId", equalTo(requestBody.get("userId")))
                .body("id",  equalTo(1))
                .body("title", equalTo(requestBody.get("title")))
                .body("body", equalTo(requestBody.get("body")));

        System.out.println("Сервер правильно обрабатывает id, не меняя его");
    }
}
