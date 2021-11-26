package api.data;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

public enum Status {
  @SerializedName("active")
  Active,
  @SerializedName("blocked")
  Blocked;
}
