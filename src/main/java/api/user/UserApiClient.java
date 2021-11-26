package api.user;

import static io.restassured.RestAssured.given;

import api.ApiClientAbs;
import com.google.gson.ExclusionStrategy;
import io.restassured.response.Response;
import api.entries.User;

public class UserApiClient extends ApiClientAbs<User> implements IUserApiClient {

  String body = "";

  public UserApiClient(User user) {
    super();
    this.body = obj2json(user);
  }

  public UserApiClient(User user, ExclusionStrategy exclusionStrategy) {
    super();
    this.body = obj2json(user, exclusionStrategy);
  }

  @Override
  public Response create() {
    return given()
        .header("Content-type", "application/json")
        .and()
        .body(this.body)
        .when()
        .post("/api/system/users")
        .then()
        .extract().response();
  }
}
