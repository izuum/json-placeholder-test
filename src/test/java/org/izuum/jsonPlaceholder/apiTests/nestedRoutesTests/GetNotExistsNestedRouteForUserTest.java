package org.izuum.jsonPlaceholder.apiTests.nestedRoutesTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-050: Получение nested route для несуществующего user")
public class GetNotExistsNestedRouteForUserTest {

    @Test
    @DisplayName("TC-050: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/users/999/posts", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-050: Ответ содержит пустой массив")
    public void checkThatResponseHasEmptyList(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/999/posts")
                .then()
                .body("$", instanceOf(List.class))
                .body("size()", equalTo(0));
    }
}
