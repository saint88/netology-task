package api.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpStatusCode {
  Ok(200),
  InternalError(500);

  private int code;
}
