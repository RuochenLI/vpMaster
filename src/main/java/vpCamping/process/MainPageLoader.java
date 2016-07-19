package vpCamping.process;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import vpCamping.object.TimeParameters;
import vpCamping.object.UserParameters;

/**
 * Created by ruli on 7/21/15.
 */
public final class MainPageLoader {

   private static final String EMAIL_ID = "txtEmail";
   private static final String PASSWORD_ID = "txtPassword";
   private static final String SEARCH_ID = "btSubmit";

   /**
    * Login main page with email and password
    */
   public static Calendar loginMainPage(WebDriver webDriver, TimeParameters timeParameter, UserParameters userParameter) {
      Calendar now = TimeChecker.loadOnePageAtDefinedTime(timeParameter);
      String url = "https://secure.fr.vente-privee.com/vp4/Login/Portal_FR.aspx";
      webDriver.get(url);
      WebElement txtEmail = webDriver.findElement(By.id(EMAIL_ID));
      txtEmail.sendKeys(userParameter.getLoginEmailValue());
      WebElement txtPassword = webDriver.findElement(By.id(PASSWORD_ID));
      txtPassword.sendKeys(userParameter.getLoginPasswordValue());
      WebElement btSubmit = webDriver.findElement(By.id(SEARCH_ID));
      btSubmit.click();
      return now;
   }
}
