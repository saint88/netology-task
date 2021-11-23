package components.popups.scheduled;

import components.card.delivery.DeliveryCardForm;
import data.Notifications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReScheduledNotification extends ScheduledNotificationAbs<ReScheduledNotification> {

  public ReScheduledNotification(WebDriver driver) {
    super(driver, Notifications.ReScheduled);
  }

  public SuccessScheduledNotification clickRePlanButton() {
    driver.findElement(By.cssSelector(this.popupSelector)).findElement(By.tagName("button")).click();

    return new SuccessScheduledNotification(driver);
  }

}
