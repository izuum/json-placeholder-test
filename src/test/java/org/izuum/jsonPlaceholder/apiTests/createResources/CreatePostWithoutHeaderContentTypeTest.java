package org.izuum.jsonPlaceholder.apiTests.createResources;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-067: Создание post без заголовка Content-Type")
public class CreatePostWithoutHeaderContentTypeTest {

    @Test
    @DisplayName("TC-067: Статус-код не 5хх, фиксация поведения")
    public void createPostWithoutHeaderContentType() {
        String jsonString = "{\"title\": \"Test\", \"body\": \"Test Body\", \"userId\": 1}";

        Response response = RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .noContentType()
                .body(jsonString.getBytes())
                .when()
                .post("/posts");

        assertTrue(response.statusCode() < 500);
        assertEquals(HttpStatus.SC_CREATED, response.statusCode());


        System.out.println("======Фиксация поведения======");
        System.out.println("Метод POST /posts");
        System.out.println("Отправляется: " + jsonString);
        System.out.println("Статус-код: " + response.getStatusCode());
        System.out.println("Ответ содержит id=" +response.jsonPath().getInt("id"));
        System.out.println("Тело ответа: " + response.getBody().asString());
        System.out.println("Сервер не знает что делать со строкой в байтах, поэтому просто создает ресурс игнорируя тело запроса");
        System.out.println("==============================");
    }
}
