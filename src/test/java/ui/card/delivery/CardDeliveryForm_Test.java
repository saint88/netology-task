package ui.card.delivery;

import annotations.Driver;
import components.card.delivery.DeliveryCardForm;
import components.popups.scheduled.ReScheduledNotification;
import components.popups.scheduled.SuccessScheduledNotification;
import data.FieldSubscriptions;
import data.generators.UserGenerator;
import data.generators.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.CardDeliveryPage;
import extensions.UIExtension;

import java.time.format.DateTimeFormatter;

@ExtendWith(UIExtension.class)
public class CardDeliveryForm_Test {

  @Driver
  public WebDriver driver;

  @Test
  public void test_schedule_meet_for_card_delivery() {
    CardDeliveryPage cardDeliveryPage = new CardDeliveryPage(driver);
    cardDeliveryPage.open();

    UserGenerator userGenerator = new UserGenerator();
    User user = userGenerator.generate();

    DeliveryCardForm deliveryCardForm = new DeliveryCardForm(driver)
        .typeUserName(user.getUsername())
        .userNameShouldBeSameAs(user.getUsername())
        .setDate(user.getMeetDate())
        .dateInFieldShouldBeSameAs(user.getMeetDate())
        .typeCityInField(user.getCity())
        .cityInFieldShouldBeSameAs(user.getCity())
        .typePhoneInField(user.getPhone())
        .phoneInFieldShouldBeSameAs(user.getPhone())
        .agreementCheckboxStateShouldBeSameAs(false)
        .setStateOfAgreementCheckbox(true)
        .agreementCheckboxStateShouldBeSameAs(true);

    SuccessScheduledNotification successScheduledNotification = new SuccessScheduledNotification(driver)
        .popupShouldNotBeVisible();

    deliveryCardForm.clickScheduledMeet();

    successScheduledNotification.popupShouldBeVisible()
        .notifyTitleShouldBeSameAs("Успешно!")
        .notifyContent(String.format("Встреча успешно запланирована на %s", user.getMeetDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
  }

  @Test
  public void test_field_subscription() {
    new CardDeliveryPage(driver).open();

    new DeliveryCardForm(driver)
        .userNameFieldSubscriptionShouldBeSameAs(FieldSubscriptions.UserName.getSubscription())
        .dateFieldSubscriptionShouldBeSameAs(FieldSubscriptions.MeetDate.getSubscription())
        .cityFieldDescribeShouldBeSameAs(FieldSubscriptions.City.getSubscription())
        .phoneFieldSubscriptionShouldBeSameAs(FieldSubscriptions.Phone.getSubscription())
        .agreementCheckboxTextShouldBeSameAs("Я соглашаюсь с условиями обработки и использования моих персональных данных")
        .securityTextInfoShouldBeSameAs("  Мы гарантируем безопасность ваших данных");
  }

  @Test
  public void replain_meet_card_delivery() {
    CardDeliveryPage cardDeliveryPage = new CardDeliveryPage(driver);
    cardDeliveryPage.open();

    UserGenerator userGenerator = new UserGenerator();
    User user = userGenerator.generate();

    DeliveryCardForm deliveryCardForm = new DeliveryCardForm(driver)
        .typeUserName(user.getUsername())
        .userNameShouldBeSameAs(user.getUsername())
        .setDate(user.getMeetDate())
        .dateInFieldShouldBeSameAs(user.getMeetDate())
        .typeCityInField(user.getCity())
        .cityInFieldShouldBeSameAs(user.getCity())
        .typePhoneInField(user.getPhone())
        .phoneInFieldShouldBeSameAs(user.getPhone())
        .agreementCheckboxStateShouldBeSameAs(false)
        .setStateOfAgreementCheckbox(true)
        .agreementCheckboxStateShouldBeSameAs(true);

    SuccessScheduledNotification successScheduledNotification = new SuccessScheduledNotification(driver);
    ReScheduledNotification reScheduledNotification = new ReScheduledNotification(driver);

    successScheduledNotification.popupShouldNotBeVisible();

    deliveryCardForm.clickScheduledMeet();

    successScheduledNotification.popupShouldBeVisible();

    reScheduledNotification.popupShouldNotBeVisible();
    deliveryCardForm.clickScheduledMeet();

    reScheduledNotification.popupShouldBeVisible()
        .notifyTitleShouldBeSameAs("Необходимо подтверждение")
        .notifyContentContains("У вас уже запланирована встреча на другую дату. Перепланировать?");

    reScheduledNotification.clickRePlanButton()
        .popupShouldBeVisible()
        .notifyTitleShouldBeSameAs("Успешно!")
        .notifyContent(String.format("Встреча успешно запланирована на %s", user.getMeetDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
  }

}
