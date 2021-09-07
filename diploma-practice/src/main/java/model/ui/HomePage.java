package model.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  //@FindBy(id = "shopping_cart_container")
  @FindBy(xpath = "//span[@class='shopping_cart_badge']")
  private WebElement shoppingCart;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public WebElement getShoppingCart() {
    return shoppingCart;
  }

  public WebElement getAddOrRemoveButton(String productName) {
    String addToCartButtonXpath = String
        .format("//*[contains(text(), '%s')]//ancestor::*[@class='inventory_item']//child::button",
            productName);
    return driver.findElement(By.xpath(addToCartButtonXpath));
  }

  public CartPage openCart() {
    shoppingCart.click();
    return new CartPage(driver);
  }
}
