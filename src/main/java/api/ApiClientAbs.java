package api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;

public abstract class ApiClientAbs<T> {

  public ApiClientAbs() {
    RestAssured.baseURI = System.getProperty("webdriver.base.url");
  }

  protected String obj2json(T obj) {
    Gson gson = new Gson();

    return gson.toJson(obj);
  }

  protected String obj2json(T obj, ExclusionStrategy exclusionStrategy) {
    Gson gson = new GsonBuilder()
        .addSerializationExclusionStrategy(exclusionStrategy)
        .create();

    return gson.toJson(obj);
  }

}
