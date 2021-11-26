package api.entries;

import api.data.Status;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SuppressWarnings("URF_UNREAD_FIELD")
public class User {
  @Expose
  private String login;
  @Expose
  private String password;
  @Expose
  private Status status;
}
