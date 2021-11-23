package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import waiters.StandartWaiter;

public abstract class AnyComponentAbs<T> {

  public WebDriver driver;
  protected StandartWaiter standartWaiter;

  public AnyComponentAbs(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    standartWaiter = new StandartWaiter(driver);
  }

}
