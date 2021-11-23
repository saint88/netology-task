package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Cities {
  Moscow("Москва"),
  Ekaterinburg("Екатеринбург");

  private String name;
}
