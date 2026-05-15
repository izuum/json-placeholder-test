package org.izuum.jsonPlaceholder.apiTests.deleteDataTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.deleteResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TC-094: Удаление photo")
public class DeletePhotoTest {

    @Test
    @DisplayName("TC-094: Статус-код 200")
    public void checkStatusCodeOfDeletePhoto() {
        Response response = deleteResource("/photos/1");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("{}", response.getBody().asString());
    }
}
