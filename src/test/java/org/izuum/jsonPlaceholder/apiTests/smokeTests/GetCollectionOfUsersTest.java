package org.izuum.jsonPlaceholder.apiTests.smokeTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCode;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-011: Получение коллекции users")
public class GetCollectionOfUsersTest {

    @Test
    @DisplayName("TC-011: Статус-код 200")
    public void getRequestCheckStatusCode(){
        checkStatusCode("/users", HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC-011: Ответ является массивом")
    public void getRequestCheckResponseIsAnArray(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users")
                .then()
                .body("$", instanceOf(List.class));
    }

    @Test
    @DisplayName("TC-011: Элементы содержат поля id, name, username, email, address, phone, website и company")
    public void getRequestCheckElementsHasFields(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users")
                .then()
                .body("every {it.containsKey('id')}", is(true))
                .body("every {it.containsKey('name')}", is(true))
                .body("every {it.containsKey('username')}", is(true))
                .body("every {it.containsKey('email')}", is(true))
                .body("every {it.containsKey('address')}", is(true))
                .body("every {it.containsKey('phone')}", is(true))
                .body("every {it.containsKey('website')}", is(true))
                .body("every {it.containsKey('company')}", is(true));
    }
}
