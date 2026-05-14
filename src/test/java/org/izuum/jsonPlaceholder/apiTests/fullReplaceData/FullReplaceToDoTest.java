package org.izuum.jsonPlaceholder.apiTests.fullReplaceData;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-080: Полная замена todo")
public class FullReplaceToDoTest {

    @Test
    @DisplayName("TC-080: Статус-код 200")
    public void checkThatStatusCodeIsOk(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("userId", 10);
        requestBody.put("title", "test to do quickly");
        requestBody.put("completed", true);

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .put("/todos/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-080: Поля возвращаются корректно, в частности completed")
    public void checkThatFieldsReturnCorrectly(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("userId", 10);
        requestBody.put("title", "test to do quickly");
        requestBody.put("completed", true);

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .log().all()
                .when()
                .put("/todos/1")
                .then()
                .body("id", equalTo(1))
                .body("userId", equalTo(requestBody.get("userId")))
                .body("title", equalTo(requestBody.get("title")))
                .body("completed", equalTo(requestBody.get("completed")));
    }
}
