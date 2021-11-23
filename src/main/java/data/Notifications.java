package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Notifications {
  Success("success-notification"),
  ReScheduled("replan-notification");

  private String id;
}
