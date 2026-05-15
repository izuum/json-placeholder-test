package org.izuum.jsonPlaceholder.apiTests.deleteDataTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.deleteResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-092: Удаление comment")
public class DeleteCommentTest {

    @Test
    @DisplayName("TC-092: Статус-код 200")
    public void checkStatusCodeOfDeleteComment() {
        Response response = deleteResource("/comments/1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("{}", response.getBody().asString());
    }
}
