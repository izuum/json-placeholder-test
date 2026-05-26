package org.izuum.jsonPlaceholder.apiTestsWithDto.smokeTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TC-011: Получить коллекцию users")
public class GetCollectionOfUsersWithDtoTest {

    @Test
    @DisplayName("TC-011: Проверить, что статус-код 200, в ответе массив, элементы содержат id, name, username, email, address" +
            "phone, website, company")
    public void getCollectionOfUsersWithDtoTest() {
        Response response = getResponse("/users");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        String body = response.getBody().asString();
        assertTrue(body.startsWith("["), "В ответе должен быть массив");

        List<User> users = response.as(new TypeRef<List<User>>() {});
        assertFalse(users.isEmpty());

        assertAll(
                users.stream().map(user -> () -> {
                    int index = users.indexOf(user);

                    assertAll(
                            () -> assertTrue(user.getId() > 0, "У user[" + index + "] поле id меньше 1"),
                            () -> assertNotNull(user.getName(), "У user[" + index + "] поле name равно null"),
                            () -> assertNotNull(user.getUsername(), "У user[" + index + "] поле username равно null"),
                            () -> assertNotNull(user.getEmail(), "У user[" + index + "] поле email равно null"),
                            () -> assertTrue(user.getEmail().contains("@"), "У user[" + index + "] в email отсутствует '@'"),
                            () -> assertNotNull(user.getAddress(), "У user[" + index + "] поле address равно null"),
                            () -> assertNotNull(user.getPhone(), "У user[" + index + "] поле phone равно null"),
                            () -> assertNotNull(user.getWebsite(), "У user[" + index + "] поле website равно null"),
                            () -> assertNotNull(user.getCompany(), "У user[" + index + "] поле company равно null")
                    );
                })
        );
    }
}
