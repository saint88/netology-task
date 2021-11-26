package api.user;

import api.entries.User;
import com.google.gson.ExclusionStrategy;
import io.restassured.response.Response;

public interface IUserApiClient {
  public Response create();
}
