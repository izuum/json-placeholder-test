package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-001: Получение коллекции posts")
public class GetCollectionOfPostsTest {

    @Test
    @DisplayName("TC-001: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/posts", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-001: Ответом является массив")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-001: Длина массива >= 1")
    public void getRequestCheckLengthOfArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts")
                .then()
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    @DisplayName("TC-001: Первый элемент имеет поля id, title, body, userId")
    public void getRequestCheckThatFirstElementHasFields(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts")
                .then()
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("title"))
                .body("[0]", hasKey("body"))
                .body("[0]", hasKey("userId"));
    }

}
