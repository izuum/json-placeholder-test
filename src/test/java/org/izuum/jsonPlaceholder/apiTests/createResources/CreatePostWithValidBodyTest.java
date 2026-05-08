package org.izuum.jsonPlaceholder.apiTests.createResources;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkStatusCodePost;

@DisplayName("TC-063: Создаение post с полным валидным body")
public class CreatePostWithValidBodyTest {

    @Test
    @DisplayName("TC-063: Статус-код 201")
    public void checkThatStatusCodeCreated(){
        checkStatusCodePost("/posts", HttpStatus.SC_CREATED);
    }
}
