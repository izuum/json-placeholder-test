package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-012: Получение user по id")
public class GetUserByIdTest {

    @Test
    @DisplayName("TC-012: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/users/1", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-012: Ответ является объектом")
    public void getRequestResponseIsAnObject(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .body("$", instanceOf(Object.class));
    }

    @Test
    @DisplayName("TC-012: Вложенные поля address и company присутствуют")
    public void getRequestCheckThatObjectHasNestedFields(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .body("", hasKey("address"))
                .body("", hasKey("company"))
                .body("address", instanceOf(Object.class))
                .body("company", instanceOf(Object.class));
    }
}
