package components.popups.scheduled;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import components.popups.AnyPopupAbs;
import data.Notifications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.StandartWaiter;

public abstract class ScheduledNotificationAbs<T> extends AnyPopupAbs<T> {

  protected StandartWaiter waiter;
  protected String popupSelector;

  public ScheduledNotificationAbs(WebDriver driver, Notifications notifications) {
    super(driver);
    waiter = new StandartWaiter(driver);
    popupSelector = String.format("[data-test-id='%s'].notification", notifications.getId());
  }


  public T popupShouldBeVisible() {
    assertTrue(waiter.waitForCondition(ExpectedConditions.attributeContains(By.cssSelector(popupSelector), "class", "notification_visible")));

    return (T)this;
  }

  public T popupShouldNotBeVisible() {
    assertTrue(waiter.waitForCondition(ExpectedConditions.not(ExpectedConditions.attributeContains(By.cssSelector(popupSelector), "class", "notification_visible"))));

    return (T) this;
  }

  public T notifyTitleShouldBeSameAs(String expectedNotifyTitle) {
    String headerSelector = popupSelector + " .notification__title";
    standartWaiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(headerSelector)));
    assertEquals(expectedNotifyTitle, driver.findElement(By.cssSelector(headerSelector)).getText(), "Notification title is wrong");

    return (T)this;
  }

  public T notifyContent(String expectedNotifyContent) {
    String contentSelector = popupSelector + " .notification__content";
    assertEquals(expectedNotifyContent, driver.findElement(By.cssSelector(contentSelector)).getText(), "Notification title is wrong");

    return (T)this;
  }

  public T notifyContentContains(String expectedNotifyContent) {
    String contentSelector = popupSelector + " .notification__content";
    assertTrue(driver.findElement(By.cssSelector(contentSelector)).getText().contains(expectedNotifyContent), "Notification title is wrong");

    return (T)this;
  }



}
