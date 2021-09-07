package model.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

  @FindBy(id = "user-name")
  private WebElement loginInput;

  @FindBy(id = "password")
  private WebElement passwordInput;

  @FindBy(id = "login-button")
  private WebElement loginButton;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public HomePage login(String username, String password) {
    loginInput.click();
    loginInput.sendKeys(username);
    passwordInput.click();
    passwordInput.sendKeys(password);
    loginButton.click();
    return new HomePage(driver);
  }
}
