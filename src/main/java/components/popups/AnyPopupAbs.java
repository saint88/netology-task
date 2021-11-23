package components.popups;

import static org.junit.jupiter.api.Assertions.assertTrue;

import components.AnyComponentAbs;
import data.Notifications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.StandartWaiter;

public abstract class AnyPopupAbs<T> extends AnyComponentAbs<T> {

  public AnyPopupAbs(WebDriver driver) {
    super(driver);
  }
}
