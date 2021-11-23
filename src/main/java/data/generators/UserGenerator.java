package data.generators;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import data.Cities;
import data.generators.entities.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class UserGenerator implements IGenerator<User> {

  private FakeValuesService fakeValuesService = new FakeValuesService(new Locale("ru-RU"), new RandomService());
  private Faker faker = new Faker();

  @Override
  public User generate() {
    return new User(
        generateUserName("[а-я]{5}"),
        new FakeCity(false).generate(),
        generateMeetDate(),
        generatePhone()
    );
  }

  public String generateUserName(String pattern) {
    return fakeValuesService.regexify(pattern);
  }

  private LocalDate generateMeetDate() {
    Date futureDate = faker.date().future(10, 3, TimeUnit.DAYS);
    return Instant.ofEpochMilli(futureDate.getTime())
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  private String generatePhone() {
    return faker.regexify("[1-9][0-9]{10}");
  }
}
