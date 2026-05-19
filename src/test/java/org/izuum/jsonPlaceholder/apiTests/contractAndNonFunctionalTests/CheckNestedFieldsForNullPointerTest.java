package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-108: Проверка вложенных полей на null-safety")
public class CheckNestedFieldsForNullPointerTest {

    @Test
    @DisplayName("TC-108: Проверка что address/company/geo доступны клиенту без ошибок NPE")
    public void checkThatFieldsAvaliableWithoutNpe(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("address", both(isA(Map.class)).and(notNullValue()))
                .body("address.street", both(isA(String.class)).and(notNullValue()))
                .body("address.suite", both(isA(String.class)).and(notNullValue()))
                .body("address.city", both(isA(String.class)).and(notNullValue()))
                .body("address.zipcode", both(isA(String.class)).and(notNullValue()))
                .body("address.geo",  both(isA(Map.class)).and(notNullValue()))
                .body("address.geo.lat",  both(isA(String.class)).and(notNullValue()))
                .body("address.geo.lng", both(isA(String.class)).and(notNullValue()))
                .body("company", both(isA(Map.class)).and(notNullValue()))
                .body("company.name", both(isA(String.class)).and(notNullValue()))
                .body("company.catchPhrase", both(isA(String.class)).and(notNullValue()))
                .body("company.bs", both(isA(String.class)).and(notNullValue()));
    }
}
