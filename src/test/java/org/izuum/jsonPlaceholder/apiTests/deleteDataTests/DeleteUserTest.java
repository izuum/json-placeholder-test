package org.izuum.jsonPlaceholder.apiTests.deleteDataTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.deleteResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-096: Удаление user")
public class DeleteUserTest {

    @Test
    @DisplayName("TC-096: Статус-код 200")
    public void checkStatusCodeOfDeleteUser() {
        Response response = deleteResource("/users/1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("{}", response.getBody().asString());
    }
}
