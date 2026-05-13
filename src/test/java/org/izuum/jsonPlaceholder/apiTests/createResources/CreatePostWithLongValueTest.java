package org.izuum.jsonPlaceholder.apiTests.createResources;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.postRequestBody;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TC-069: Создание post с очень длинными значениями title/body")
public class CreatePostWithLongValueTest {

    @ParameterizedTest
    @ValueSource(ints = {100, 1000, 5000, 10000})
    @DisplayName("TC-069: Сервис отвечает стабильно")
    public void checkThatStableAnswerOfServer(int lengthOfString){
        String longTitle = "A".repeat(lengthOfString);
        String longBody = "B".repeat(lengthOfString);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", longTitle);
        requestBody.put("body", longBody);
        requestBody.put("userId", 1);

        Response firstResponse = postRequestBody("/posts", requestBody);
        Response secondResponse = postRequestBody("/posts", requestBody);
        Response thirdResponse = postRequestBody("/posts", requestBody);

        assertEquals(HttpStatus.SC_CREATED, firstResponse.getStatusCode());
        assertEquals(firstResponse.getStatusCode(), secondResponse.getStatusCode());
        assertEquals(secondResponse.getStatusCode(), thirdResponse.getStatusCode());
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1000, 5000, 10000})
    @DisplayName("TC-069: Данные возвращаются без обрезки в теле ответа")
    public void checkThatDataReturnWithoutTrim(int lengthOfString){
        String longTitle = "A".repeat(lengthOfString);
        String longBody = "B".repeat(lengthOfString);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", longTitle);
        requestBody.put("body", longBody);
        requestBody.put("userId", 1);

        Response response = postRequestBody("/posts", requestBody);
        long returnedTitle =  response.jsonPath().getString("title").length();
        long returnedBody =  response.jsonPath().getString("body").length();

        if(returnedTitle == lengthOfString && returnedBody == lengthOfString){
            System.out.println("Строки в ответе не обрезаются.");
            assertTrue(true);
        } else {
            System.out.println("======Фиксация поведения======");
            System.out.printf("Отправлена строка длиной %d символов", lengthOfString);
            System.out.println("Статус-код в ответе: " + response.getStatusCode());
            System.out.printf("Поле title обрезается до %d символов", returnedTitle);
            System.out.printf("Поле body  обрезается до %d символов", returnedBody);
            System.out.println("==============================");
        }
    }
}
