import model.ui.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  private static final String USRNAME = "standard_user";
  private static final String PSWRD = "secret_sauce";

  @Test
  public void loginTest() {
    HomePage homePage = loginPage.login(USRNAME, PSWRD);
    Assert.assertTrue(homePage.getShoppingCart().isDisplayed(),
        "Element is not displayed. You are still at Login page");
  }
}