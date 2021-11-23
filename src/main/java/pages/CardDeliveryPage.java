package pages;

import annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;

@UrlPrefix("/")
public class CardDeliveryPage extends AnyPageAbs<CardDeliveryPage> {

  public CardDeliveryPage(WebDriver driver) {
    super(driver);
  }
}
