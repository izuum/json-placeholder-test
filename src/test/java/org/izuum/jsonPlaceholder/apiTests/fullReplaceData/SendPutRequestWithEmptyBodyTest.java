package org.izuum.jsonPlaceholder.apiTests.fullReplaceData;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-077: Отправка put-запроса с пустым body")
public class SendPutRequestWithEmptyBodyTest {

    @Test
    @DisplayName("TC-077: Статус-код не 5хх")
    public void checkThatStatusCodeNotServerError(){
        RestAssured.given()
                .spec(requestSpecification())
                .body("")
                .log().all()
                .when()
                .put("/posts/1")
                .then()
                .statusCode(lessThan(HttpStatus.SC_INTERNAL_SERVER_ERROR))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-077: Проверка соответствия контракту")
    public void checkThatResponseCorrespondsContract(){
        RestAssured.given()
                .spec(requestSpecification())
                .body("")
                .log().all()
                .when()
                .put("/posts/1")
                .then()
                .body("id", equalTo(1))
                .body("$", not(hasKey("title")))
                .body("$", not(hasKey("body")))
                .body("$", not(hasKey("userId")));
    }
}
