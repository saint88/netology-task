package api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import annotations.Bug;
import api.data.HttpStatusCode;
import api.data.Status;
import api.entries.User;
import api.user.IUserApiClient;
import api.user.UserApiClient;
import com.github.javafaker.Faker;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import extensions.APIExtension;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;

@ExtendWith(APIExtension.class)
public class CreateUser_Test {

  private Faker faker = new Faker();

  @Test
  public void test_create_user_with_login_and_password() {
    String login = faker.regexify("\\w{5}");
    String password = faker.regexify("\\w{7}");

    User user = new User(
        login,
        password,
        Status.Active
    );

    IUserApiClient userApiClient = new UserApiClient(user);

    Response resp = userApiClient.create();
    assertEquals(HttpStatusCode.Ok.getCode(), resp.statusCode(),
        String.format("User with login=%s and password=%s doesn't create", login, password));
  }

  @Test
  public void test_create_blocked_user() {
    String login = faker.regexify("\\w{5}");
    String password = faker.regexify("\\w{7}");

    User user = new User(
        login,
        password,
        Status.Blocked
    );

    IUserApiClient userApiClient = new UserApiClient(user);

    Response resp = userApiClient.create();
    assertEquals(HttpStatusCode.Ok.getCode(), resp.statusCode(),
        String.format("Blocked user with login=%s and password=%s doesn't create", login, password));
  }

  @Test
  public void test_create_user_without_login() {
    String password = faker.regexify("\\w{7}");
    User user = new User(
        "",
        password,
        Status.Active
    );

    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes field) {
        if (field.getDeclaringClass() == User.class && field.getName().equals("login")) {
          return true;
        }

        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    IUserApiClient userApiClient = new UserApiClient(user, strategy);
    Response resp = userApiClient.create();
    assertEquals(HttpStatusCode.InternalError.getCode(), resp.statusCode(),
        String.format("User without login and password=%s is create", password));
  }

  @Test
  @Bug("Create user with empty login")
  public void test_create_user_with_empty_login() {
    String password = faker.regexify("\\w{7}");
    User user = new User(
        "",
        password,
        Status.Active
    );

    IUserApiClient userApiClient = new UserApiClient(user);
    Response resp = userApiClient.create();
    assertEquals(HttpStatusCode.InternalError.getCode(), resp.statusCode(),
        String.format("User with empty login and password=%s is create", password));
  }

  @Test
  public void test_create_user_without_password() {
    String login = faker.regexify("\\w{5}");
    User user = new User(
        login,
        "",
        Status.Active
    );

    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes field) {
        if (field.getDeclaringClass() == User.class && field.getName().equals("password")) {
          return true;
        }

        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    IUserApiClient userApiClient = new UserApiClient(user, strategy);
    Response resp = userApiClient.create();
    assertEquals(HttpStatusCode.InternalError.getCode(), resp.statusCode(),
        String.format("User without password and login=%s is create", login));
  }

  @Test
  @Bug("Create user with empty password")
  public void test_create_user_with_empty_password() {
    String login = faker.regexify("\\w{5}");
    User user = new User(
        login,
        "",
        Status.Active
    );

    IUserApiClient userApiClient = new UserApiClient(user);
    Response resp = userApiClient.create();
    assertEquals(HttpStatusCode.InternalError.getCode(), resp.statusCode(),
        String.format("User with empty password and login=%s is create", login));
  }

  @Test
  public void test_create_user_without_status() {
    String login = faker.regexify("\\w{5}");
    String password = faker.regexify("\\w{7}");
    User user = new User(
        login,
        password,
        null
    );

    ExclusionStrategy strategy = new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes field) {
        if (field.getDeclaringClass() == User.class && field.getName().equals("status")) {
          return true;
        }

        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    };

    IUserApiClient userApiClient = new UserApiClient(user, strategy);
    Response resp = userApiClient.create();
    assertEquals(HttpStatusCode.InternalError.getCode(), resp.statusCode(),
        "User without status is create");
  }
}
