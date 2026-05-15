package org.izuum.jsonPlaceholder.apiTests.deleteDataTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-091: Удаление несуществующего ресурса")
public class DeleteNotExistsPostTest {

    @Test
    @DisplayName("TC-091: Статус-код не 5хх, фиксация контракта")
    public void checkStatusCodeOfDeleteNotExistsPost() {
        Response response = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .delete("/posts/9999")
                .then()
                .statusCode(lessThan(HttpStatus.SC_INTERNAL_SERVER_ERROR))
                .extract().response();

        System.out.println("======Фиксация контракта======");
        System.out.println("Отправлен DELETE /posts/9999");
        System.out.println("Статус-код: " + response.getStatusCode());
        System.out.println("Тело ответа: " + response.getBody().asString());
        System.out.println("==============================");
    }
}
