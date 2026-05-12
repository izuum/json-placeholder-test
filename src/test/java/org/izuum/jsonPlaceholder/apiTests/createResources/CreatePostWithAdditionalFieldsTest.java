package org.izuum.jsonPlaceholder.apiTests.createResources;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.postRequestBody;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-065: Создать post с дополнительными неописанными полями")
public class CreatePostWithAdditionalFieldsTest {

    @Test
    @DisplayName("TC-065: Стабильный ответ сервера")
    public void checkStableResponse(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("title", "test title");
        requestBody.put("body",  "test body");

        Response firstPostResponse = postRequestBody("/posts", requestBody);
        Response secondPostResponse = postRequestBody("/posts", requestBody);
        Response thirdPostResponse = postRequestBody("/posts", requestBody);

        assertEquals(HttpStatus.SC_CREATED,firstPostResponse.getStatusCode());
        assertEquals(firstPostResponse.getStatusCode(),secondPostResponse.getStatusCode());
        assertEquals(secondPostResponse.getStatusCode(),thirdPostResponse.getStatusCode());
    }

    @Test
    @DisplayName("TC-065: Проверка возвращения дополнительных полей")
    public void checkReturnExtraFields(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("title", "test title");
        requestBody.put("body",  "test body");
        requestBody.put("extraFields", "test extraFields");
        requestBody.put("anotherExtraFields", 12345);

        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", equalTo(101))
                .body("title", equalTo("test title"))
                .body("body", equalTo("test body"))
                .body("extraFields", equalTo("test extraFields"))
                .body("anotherExtraFields", equalTo(12345));
    }
}
