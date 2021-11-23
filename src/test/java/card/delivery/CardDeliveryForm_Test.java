package card.delivery;

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
import runners.UIExtension;

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
        .notifyTitleShouldBeSameAs("Успешно!");
  }

  @Test
  public void test_field_subscription() {
    CardDeliveryPage cardDeliveryPage = new CardDeliveryPage(driver);
    cardDeliveryPage.open();

    DeliveryCardForm deliveryCardForm = new DeliveryCardForm(driver);
    deliveryCardForm.userNameFieldSubscriptionShouldBeSameAs(FieldSubscriptions.UserName.getSubscription());
    deliveryCardForm.dateFieldSubscriptionShouldBeSameAs(FieldSubscriptions.MeetDate.getSubscription());
    deliveryCardForm.cityFieldDescribeShouldBeSameAs(FieldSubscriptions.City.getSubscription());
    deliveryCardForm.phoneFieldSubscriptionShouldBeSameAs(FieldSubscriptions.Phone.getSubscription());

    deliveryCardForm.agreementCheckboxTextShouldBeSameAs("Я соглашаюсь с условиями обработки и использования моих персональных данных");
    deliveryCardForm.securityTextInfoShouldBeSameAs("  Мы гарантируем безопасность ваших данных");
  }

  @Test
  public void replain_meet_card_delivery() {
    CardDeliveryPage cardDeliveryPage = new CardDeliveryPage(driver);
    cardDeliveryPage.open();

    UserGenerator userGenerator = new UserGenerator();
    User user = userGenerator.generate();

    DeliveryCardForm deliveryCardForm = new DeliveryCardForm(driver);
    deliveryCardForm.typeUserName(user.getUsername());
    deliveryCardForm.userNameShouldBeSameAs(user.getUsername());

    deliveryCardForm.setDate(user.getMeetDate());
    deliveryCardForm.dateInFieldShouldBeSameAs(user.getMeetDate());

    deliveryCardForm.typeCityInField(user.getCity());
    deliveryCardForm.cityInFieldShouldBeSameAs(user.getCity());

    deliveryCardForm.typePhoneInField(user.getPhone());
    deliveryCardForm.phoneInFieldShouldBeSameAs(user.getPhone());

    deliveryCardForm.agreementCheckboxStateShouldBeSameAs(false);
    deliveryCardForm.setStateOfAgreementCheckbox(true);
    deliveryCardForm.agreementCheckboxStateShouldBeSameAs(true);

    SuccessScheduledNotification successScheduledNotification = new SuccessScheduledNotification(driver);
    ReScheduledNotification reScheduledNotification = new ReScheduledNotification(driver);

    successScheduledNotification.popupShouldNotBeVisible();

    deliveryCardForm.clickScheduledMeet();

    successScheduledNotification.popupShouldBeVisible();

    reScheduledNotification.popupShouldNotBeVisible();
    deliveryCardForm.clickScheduledMeet();

    reScheduledNotification.popupShouldBeVisible();
    reScheduledNotification.notifyTitleShouldBeSameAs("Необходимо подтверждение");

    reScheduledNotification.clickRePlanButton()
        .popupShouldBeVisible()
        .notifyTitleShouldBeSameAs("Успешно!");
  }

}
