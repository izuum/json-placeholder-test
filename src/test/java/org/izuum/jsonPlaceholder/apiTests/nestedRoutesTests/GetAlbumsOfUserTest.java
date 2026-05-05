package org.izuum.jsonPlaceholder.apiTests.nestedRoutesTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-041: Получение albums пользователя")
public class GetAlbumsOfUserTest {

    @Test
    @DisplayName("TC-041: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/users/1/albums", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-041: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1/albums")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-041: Каждый элемент имеет userId=1")
    public void checkThatEveryElementsHasUserIdIsOne(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1/albums")
                .then()
                .body("userId", everyItem(equalTo(1)));
    }

}
