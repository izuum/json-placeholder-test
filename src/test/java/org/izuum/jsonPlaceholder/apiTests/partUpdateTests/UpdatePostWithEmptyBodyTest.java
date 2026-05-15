package org.izuum.jsonPlaceholder.apiTests.partUpdateTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-084: Обновление post пустым телом")
public class UpdatePostWithEmptyBodyTest {

    @Test
    @DisplayName("TC-084: Статус-код не 5хх, фиксация поведения")
    public void checkThatStatusCodeNotServerError(){
        Response response = RestAssured.given()
                .spec(requestSpecification())
                .body("")
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(lessThan(HttpStatus.SC_INTERNAL_SERVER_ERROR))
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .extract()
                .response();

        System.out.println("======Фиксация поведения======");
        System.out.println("Отправлен PATCH /posts/1 с пустым телом");
        System.out.println("Статус-код ответа: " + response.getStatusCode());
        System.out.println("Тело ответа: " + response.getBody().asString());
        System.out.println("===============================");
    }
}
