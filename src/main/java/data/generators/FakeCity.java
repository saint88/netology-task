package data.generators;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import data.Cities;

public class FakeCity implements IGenerator<String> {

  private Faker faker = new Faker();
  private RandomService randomService = new RandomService();

  private boolean isFake;

  public FakeCity(boolean isFake) {
    this.isFake = isFake;
  }

  @Override
  public String generate() {
    if(isFake) {
      return faker.regexify("[а-я]{10}");
    }

    Cities[] cities = Cities.values();
    return cities[randomService.nextInt(cities.length)].getName();
  }
}
