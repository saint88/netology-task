package components.card.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import components.AnyComponentAbs;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeliveryCardForm extends AnyComponentAbs<DeliveryCardForm> {

  public DeliveryCardForm(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "h2")
  private WebElement formHeader;

  @FindBy(css = "[data-test-id='city'] input")
  private WebElement cityField;
  @FindBy(css = "[data-test-id='city'] .input__sub")
  private WebElement cityFieldSubscription;

  @FindBy(css = "[data-test-id='date'] input[type='date'], [data-test-id='date'] input[type='tel']")
  private WebElement dateField;
  @FindBy(css = "[data-test-id='date'] .input__sub")
  private WebElement dateFieldSubscription;

  @FindBy(css = "[data-test-id='name'] input[type='text']")
  private WebElement nameField;
  @FindBy(css = "[data-test-id='name'] .input__sub")
  private WebElement nameFieldSubscription;

  @FindBy(css = "[data-test-id='phone'] input[type='text'], [data-test-id='phone'] input[type='tel']")
  private WebElement phoneField;
  @FindBy(css = "[data-test-id='phone'] .input__sub")
  private WebElement phoneFieldSubscription;

  @FindBy(css = "[data-test-id='agreement'] input[type='checkbox']")
  private WebElement agreementCheckbox;
  @FindBy(css = "label.checkbox")
  private WebElement checkboxLabel;
  @FindBy(css = "[data-test-id='agreement'] .checkbox__text")
  private WebElement agreementCheckboxText;

  @FindBy(css = ".paragraph")
  private WebElement securityTextInfo;

  @FindBy(css = ".form-field > button")
  private WebElement submitButton;

  public DeliveryCardForm formHeaderShouldBeSameAs(String expectedFormHeader) {
    assertEquals(expectedFormHeader, formHeader.getText(), String.format("Form header should be same as %s", expectedFormHeader));

    return this;
  }

  public DeliveryCardForm typeCityInField(String city) {
    cityField.sendKeys(city, Keys.ESCAPE);

    return this;
  }

  public DeliveryCardForm cityInFieldShouldBeSameAs(String expectedCity) {
    assertEquals(expectedCity, cityField.getAttribute("value"), String.format("Form header should be same as %s", expectedCity));

    return this;
  }

  public DeliveryCardForm cityFieldDescribeShouldBeSameAs(String expectedCityFieldDescription) {
    assertEquals(expectedCityFieldDescription, cityFieldSubscription.getText(), "City field description is frong");

    return this;
  }

  public DeliveryCardForm typeUserName(String name) {
    nameField.sendKeys(name);

    return this;
  }

  public DeliveryCardForm userNameShouldBeSameAs(String expectedName) {
    assertEquals(expectedName, nameField.getAttribute("value"), String.format("Username is field should be same as %s", expectedName));

    return this;
  }

  public DeliveryCardForm userNameFieldSubscriptionShouldBeSameAs(String expectedSubscription) {
    assertEquals(expectedSubscription, nameFieldSubscription.getText(), String.format("Subscription of username field should be same as %s", expectedSubscription));

    return this;
  }

  public DeliveryCardForm setDate(LocalDate date) {
    dateField.click();
    for(int i = 0; i < 8; i++) {
      dateField.sendKeys(Keys.BACK_SPACE);
    }

    dateField.sendKeys(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), Keys.ESCAPE);

    return this;
  }

  public DeliveryCardForm dateInFieldShouldBeSameAs(LocalDate expectedDate) {
    assertEquals(expectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), dateField.getAttribute("value"), "Date in field is wrong");

    return this;
  }

  public DeliveryCardForm dateFieldSubscriptionShouldBeSameAs(String expectedDateFieldSubscription) {
    assertEquals(expectedDateFieldSubscription, dateFieldSubscription.getText(), "Date field subscription is wrong");

    return this;
  }

  public DeliveryCardForm typePhoneInField(String phone) {
    phoneField.sendKeys(phone);

    return this;
  }

  public DeliveryCardForm phoneInFieldShouldBeSameAs(String expectedPhone) {
    assertEquals(expectedPhone.replaceAll("\\.", ""),
        phoneField.getAttribute("value").replace("+", "").replaceAll("\\s+", ""),
        "Phone in field is wrong");

    return this;
  }

  public DeliveryCardForm phoneFieldSubscriptionShouldBeSameAs(String expectedPhoneFieldSubscription) {
    assertEquals(expectedPhoneFieldSubscription, phoneFieldSubscription.getText(), "Phone field subscription is wrong");

    return this;
  }

  public DeliveryCardForm setStateOfAgreementCheckbox(boolean state) {
    if(agreementCheckbox.isSelected() != state) {
      checkboxLabel.click();
    }

    return this;
  }

  public DeliveryCardForm agreementCheckboxTextShouldBeSameAs(String expectedAgreementText) {
    assertEquals(expectedAgreementText, agreementCheckboxText.getText(), "Agreement checkbox text is wrong");

    return this;
  }

  public DeliveryCardForm agreementCheckboxStateShouldBeSameAs(boolean expectedCheckboxState) {
    assertEquals(expectedCheckboxState, agreementCheckbox.isSelected(), "Agreement checkbox state is wrong");

    return this;
  }

  public DeliveryCardForm securityTextInfoShouldBeSameAs(String expectedSecurityTextInfo) {
    assertEquals(expectedSecurityTextInfo, securityTextInfo.getText(), "Security info text is wrong");

    return this;
  }

  public void clickScheduledMeet() {
    standartWaiter.waitForCondition(ExpectedConditions.elementToBeClickable(submitButton));
    submitButton.click();
  }
}
