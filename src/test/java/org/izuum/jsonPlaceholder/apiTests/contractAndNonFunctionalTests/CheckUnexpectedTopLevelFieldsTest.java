package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-107: Проверка отсутствия неожиданных полей верхнего уровня")
public class CheckUnexpectedTopLevelFieldsTest {

    @Test
    @DisplayName("TC-107: Проверка наличия полей верхнего уровня у user")
    public void checkUnexpectedTopLevelFields() {
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", both(isA(Integer.class)).and(notNullValue()))
                .body("name", both(isA(String.class)).and(notNullValue()))
                .body("username", both(isA(String.class)).and(notNullValue()))
                .body("email", both(isA(String.class)).and(notNullValue()))
                .body("address", both(isA(Map.class)).and(notNullValue()))
                .body("phone",  both(isA(String.class)).and(notNullValue()))
                .body("website", both(isA(String.class)).and(notNullValue()))
                .body("company", both(isA(Map.class)).and(notNullValue()));
    }

    @Test
    @DisplayName("TC-107: Проверка в целом набор ключей и отсутствие лишних")
    public void checkSuiteOfKeyAndLackUnexpected(){
        Response response = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("id"))
                .body("$", hasKey("name"))
                .body("$", hasKey("username"))
                .body("$", hasKey("email"))
                .body("$", hasKey("address"))
                .body("$", hasKey("phone"))
                .body("$", hasKey("website"))
                .body("$", hasKey("company"))
                .extract().response();

        Map<String, Object> user = response.jsonPath().getMap("$");

        assertThat(user.keySet(), hasSize(8));
    }

    @Test
    @DisplayName("TC-107: Проверка стабильности")
    public void checkStableAnswer(){
        Response responseOne = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1");

        Response responseTwo = RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/users/1");

        Map<String, Object> userOne = responseOne.jsonPath().getMap("$");
        Map<String, Object> userTwo = responseTwo.jsonPath().getMap("$");

        assertThat(userOne.keySet(), equalTo(userTwo.keySet()));

        assertThat(userOne.get("id"), equalTo(userTwo.get("id")));
        assertThat(userOne.get("name"), equalTo(userTwo.get("name")));
        assertThat(userOne.get("username"), equalTo(userTwo.get("username")));

        System.out.println("Контракт стабилен между запросами");
    }
}
