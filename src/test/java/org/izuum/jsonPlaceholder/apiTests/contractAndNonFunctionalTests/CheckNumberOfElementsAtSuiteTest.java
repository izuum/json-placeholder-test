package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-114: Проверка количества элементов набора")
public class CheckNumberOfElementsAtSuiteTest {

    @Test
    @DisplayName("TC-114: Фиксация ожидаемого размера набора")
    public void checkNumberOfElementsAtSuiteTest() {
        List<Integer> idsCount = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getList("id");

        int sizeOfSuite = idsCount.size();

        System.out.println("======Фиксация контракта======");
        System.out.println("Отправлен GET /posts");
        System.out.println("Размер набора: " + sizeOfSuite);
        System.out.println("==============================");
    }
}
