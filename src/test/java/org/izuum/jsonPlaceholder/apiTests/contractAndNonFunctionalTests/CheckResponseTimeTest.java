package org.izuum.jsonPlaceholder.apiTests.contractAndNonFunctionalTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.izuum.jsonPlaceholder.specification.Specification.requestSpecification;

@DisplayName("TC-106: Проверка времени ответа коллекции post")
public class CheckResponseTimeTest {

    @Test
    @DisplayName("TC-106: Проверка что время ответа в пределах SLA команды")
    public void checkResponseTime() {
        RestAssured.given()
                .spec(requestSpecification())
                .when()
                .get("/posts")
                .then()
                .time(lessThan(1000L));
    }
}
