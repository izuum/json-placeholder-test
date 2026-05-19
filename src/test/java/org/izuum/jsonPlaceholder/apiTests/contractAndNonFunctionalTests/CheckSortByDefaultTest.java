package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-112: Проверка сортировки по умолчанию")
public class CheckSortByDefaultTest {

    @Test
    @DisplayName("TC-112: Фиксация фактического порядка id в ответе")
    public void checkThatSortByAscOrder(){
        List<Integer> ids = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getList("id");

        for(int i = 1; i < ids.size(); i++){
            assertTrue(ids.get(i - 1) <= ids.get(i), "Нарушен порядок id");
        }
    }

    @Test
    @DisplayName("TC-112: Проверка на пропуски в последовательности")
    public void checkThatLackGapsInSequence(){
        List<Integer> ids = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getList("id");

        for (int i = 0; i < ids.size(); i++){
            assertEquals(Integer.valueOf(i + 1), ids.get(i), "в последовательности пропущены id");
        }
    }
}
