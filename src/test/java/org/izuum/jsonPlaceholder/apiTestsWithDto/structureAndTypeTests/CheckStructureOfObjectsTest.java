package org.izuum.jsonPlaceholder.apiTestsWithDto.structureAndTypeTests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Post;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.Todo;
import org.izuum.jsonPlaceholder.apiTestsWithDto.dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.izuum.jsonPlaceholder.apiTestUtils.ApiTestUtils.getResponse;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка структуры объектов на каждом эндпоинте")
public class CheckStructureOfObjectsTest {

    @Test
    @DisplayName("TC-019: Проверка структуры address")
    public void checkStructureOfFieldsAddress() {
        Response response = getResponse("/users/1");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        User user = response.as(User.class);
        assertNotNull(user);

        assertAll(
                () -> assertNotNull(user.getAddress().getStreet(), "street равен null"),
                () -> assertNotNull(user.getAddress().getSuite(), "suite равен null"),
                () -> assertNotNull(user.getAddress().getCity(), "city равен null"),
                () -> assertNotNull(user.getAddress().getZipcode(), "zipcode равен null"),
                () -> assertNotNull(user.getAddress().getGeo(), "geo равен null")
        );
    }

    @Test
    @DisplayName("TC-020: Проверка структуры geo")
    public void checkStructureOfFieldsGeo() {
        Response response = getResponse("/users/1");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        User user = response.as(User.class);
        assertNotNull(user);

        assertAll(
                () -> assertNotNull(user.getAddress().getGeo().getLat(), "lat равен null"),
                () -> assertInstanceOf(String.class, user.getAddress().getGeo().getLat()),
                () -> assertNotNull(user.getAddress().getGeo().getLng(), "lng равен null"),
                () -> assertInstanceOf(String.class, user.getAddress().getGeo().getLng())
        );
    }

    @Test
    @DisplayName("TC-021: Проверка структуры company")
    public void checkStructureOfFieldsCompany() {
        Response response = getResponse("/users/1");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        User user = response.as(User.class);
        assertNotNull(user);

        assertAll(
                () -> assertNotNull(user.getCompany().getName(), "name равен null"),
                () -> assertNotNull(user.getCompany().getCatchPhrase(), "catchPhrase равен null"),
                () -> assertNotNull(user.getCompany().getBs(), "bs равен null")
        );
    }

    @Test
    @DisplayName("TC-022: Проверить однородность массива posts")
    public void checkEqualityOfFieldsPosts() {
        Response response = getResponse("/posts");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        List<Post> posts = response.as(new TypeRef<List<Post>>() {});
        assertNotNull(posts);

        assertAll(
                posts.stream().map(post -> () -> {
                    int index = posts.indexOf(post);

                    assertAll(
                            () -> assertTrue(post.getUserId() > 0, "У post[" + index + "] отсутствует поле userId"),
                            () -> assertTrue(post.getId() > 0, "У post[" + index + "] отсутствует поле id"),
                            () -> assertNotNull(post.getTitle(), "У post[" + index + "] отсутствует поле title"),
                            () -> assertNotNull(post.getBody(), "У post[" + index + "] отсутствует поле body")
                    );
                })
        );
    }

    @Test
    @DisplayName("TC-023: Проверить однородность массива users")
    public void checkEqualityOfFieldsUsers() {
        Response response = getResponse("/users");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        List<User> users = response.as(new TypeRef<List<User>>() {});
        assertNotNull(users);

        assertAll(
                users.stream().map(user -> () -> {
                    int index = users.indexOf(user);

                    assertAll(
                            () -> assertNotNull(user.getAddress(), "У user[" + index + "] отсутствует поле address"),
                            () -> assertNotNull(user.getCompany(), "У user[" + index + "] отсутствует поле company")
                    );
                })
        );
    }

    @Test
    @DisplayName("TC-024: Проверить допустимость completed")
    public void checkEqualityOfFieldsCompleted() {
        Response response = getResponse("/todos");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        List<Todo> todos = response.as(new TypeRef<List<Todo>>() {});
        assertNotNull(todos);

        assertAll(
                todos.stream().map(todo -> () -> {
                    int index = todos.indexOf(todo);
                    Boolean value = todo.getCompleted();

                    assertAll(
                            () -> assertTrue(Boolean.TRUE.equals(value) || Boolean.FALSE.equals(value),
                                    "У todo[" + index + "] неверное значение(null)"),
                            () -> assertNotNull(todo.getCompleted())
                    );
                })
        );
    }
}
