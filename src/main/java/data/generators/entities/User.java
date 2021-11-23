package data.generators.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class User {
  private String username;
  private String city;
  private LocalDate meetDate;
  private String phone;
}
