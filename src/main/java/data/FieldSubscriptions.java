package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FieldSubscriptions {
  UserName("Укажите точно как в паспорте"),
  City("Выберите ваш город"),
  MeetDate("Выберите дату встречи с представителем банка"),
  Phone("На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно.");

  private String subscription;
}
