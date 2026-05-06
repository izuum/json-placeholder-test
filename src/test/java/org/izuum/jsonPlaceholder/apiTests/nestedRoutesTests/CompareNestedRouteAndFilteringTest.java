package org.izuum.jsonPlaceholder.apiTests.nestedRoutesTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.checkSetsOfId;

@DisplayName("Сравнение nested route с фильтрациями")
public class CompareNestedRouteAndFilteringTest {

    @Test
    @DisplayName("TC-044: Фильтрация для comments. Наборы id в обоих ответах идентичны")
    public void checkThatSetsOfIdOfCommentsAreEquals(){
        checkSetsOfId("/posts/1/comments", "/comments?postId=1", "id");
    }

    @Test
    @DisplayName("TC-045: Фильтрация для photos. Наборы id идентичны")
    public void checkThatSetsOfIdOfPhotosAreEquals(){
        checkSetsOfId("/albums/1/photos ", "/photos?albumId=1","id");
    }

    @Test
    @DisplayName("TC-046: Фильтрация для posts. Наборы id идентичны")
    public void checkThatSetsOfIdOfPostsAreEquals(){
        checkSetsOfId("/users/1/posts", "/posts?userId=1", "id");
    }

    @Test
    @DisplayName("TC-047: Фильтрация для ToDos. Наборы id идентичны")
    public void checkThatSetsOfIdOfTodosAreEquals(){
        checkSetsOfId("/users/1/todos", "/todos?userId=1", "id");
    }

    @Test
    @DisplayName("TC-048: Фильтрация для Album. Наборы id идентичны")
    public void checkThatSetsOfIdOfAlbumAreEquals(){
        checkSetsOfId("/users/1/albums", "/albums?userId=1", "id");
    }
}
