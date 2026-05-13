package org.izuum.jsonPlaceholder.apiTests.createResources;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-068:(БАГ) Создание post с невалидным JSON Payload")
public class CreatePostWithInvalidJsonPayloadTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "{ title: 'broken' ",           // Нет кавычек у ключа
            "{ \"title\": \"test\" ",       // Нет закрывающей скобки
            "{ \"title\": test }",          // Значение без кавычек
            "{ \"title\": \"test\", }",     // Лишняя запятая
            "not a json at all",            // Вообще не JSON
            "null"
    })
    @DisplayName("TC-068:(БАГ) Клиент/сервер корректно обрабатывает ошибку")
    public void checkThatClientAndServerHandlesErrorCorrectly(String invalidJson) {
        Response response = RestAssured.given()
                .spec(requestSpecification())
                .body(invalidJson)
                .log().all()
                .when()
                .post("/posts");

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusCode());

        System.out.println("======Фиксация поведения======");
        System.out.println("Отправленный JSON содержит: " + invalidJson);
        System.out.println("Статус-код: " + response.getStatusCode());
        System.out.println("Тело ответа содержит: " + response.getBody().asString());
        System.out.println("Вывод: БАГ! сервер возвращает ошибку, раскрывает стектрейс клиенту");
        System.out.println("==============================");
    }
}
