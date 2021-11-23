package components.popups.scheduled;

import data.Notifications;
import org.openqa.selenium.WebDriver;

public class SuccessScheduledNotification extends ScheduledNotificationAbs<SuccessScheduledNotification> {

  public SuccessScheduledNotification(WebDriver driver) {
    super(driver, Notifications.Success);
  }

}
