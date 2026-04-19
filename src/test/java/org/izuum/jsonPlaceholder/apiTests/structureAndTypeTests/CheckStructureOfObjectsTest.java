package org.izuum.jsonPlaceholder.apiTests.structureAndTypeTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("Проверка структуры объектов на каждом эндпоинте")
public class CheckStructureOfObjectsTest {

    @Test
    @DisplayName("TC-019: Проверка структуры address у объекта user")
    public void checkStructureOfAddress(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .body("address", hasKey("street"))
                .body("address", hasKey("suite"))
                .body("address", hasKey("city"))
                .body("address", hasKey("zipcode"))
                .body("address", hasKey("geo"));
    }

    @Test
    @DisplayName("TC-020: Проверка структуры geo у объекта user.address")
    public void checkStructureOfGeo(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .body("address.geo", hasKey("lat"))
                .body("address.geo", hasKey("lng"))
                .body("address.geo.lat", instanceOf(String.class))
                .body("address.geo.lng", instanceOf(String.class));
    }

    @Test
    @DisplayName("TC-021: Проверка структуры company у объекта user")
    public void checkStructureOfCompany(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .body("company", hasKey("name"))
                .body("company", hasKey("catchPhrase"))
                .body("company", hasKey("bs"));
    }

    @Test
    @DisplayName("TC-022: Проверка однородности массива posts")
    public void checkEqualityOfFieldsOfPosts(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts")
                .then()
                .body("every {it.containsKey('userId')}", is(true))
                .body("every {it.containsKey('id')}", is(true))
                .body("every {it.containsKey('title')}", is(true))
                .body("every {it.containsKey('body')}", is(true));
    }

    @Test
    @DisplayName("TC-023: Проверка однородности массива users")
    public void checkEqualityOfFieldsOfUsers(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users")
                .then()
                .body("every {it.containsKey('address')}", is(true))
                .body("every {it.containsKey('company')}", is(true));
    }

    @Test
    @DisplayName("TC-024: Проверка допустимости поля completed")
    // Проверка, что каждое значение completed соответствует типу Boolean либо true, либо false, не String и не Integer
    public void checkValidCompletedField(){
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/todos")
                .then()
                .body("completed.findAll {it != null}", everyItem(isA(Boolean.class)))
                .body("completed", not(hasItem(isA(String.class))))
                .body("completed", not(hasItem(isA(Integer.class))));
    }
}
