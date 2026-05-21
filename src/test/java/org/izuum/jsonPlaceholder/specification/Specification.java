package org.izuum.jsonPlaceholder.specification;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specification {
    static {
        RestAssured.filters(new AllureRestAssured());
        System.out.println("Allure запущен");
    }
    public static RequestSpecification requestSpecification(){
//        return new RequestSpecBuilder()
//                .setBaseUri("https://jsonplaceholder.typicode.com")
//                .setContentType(ContentType.JSON)
//                .setAccept(ContentType.JSON)
//                .log().ifValidationFails()
//                .build();

        return RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().ifValidationFails();
    }
}
