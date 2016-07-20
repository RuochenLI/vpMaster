package vpCamping.process;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vpCamping.object.SaleParameters;
import vpCamping.object.TimeParameters;
import vpCamping.object.UserParameters;

import java.util.Calendar;


/**
 * process all Business logic
 *
 * @author LRC
 */
public final class MainProcess extends Thread {

   private static final Logger LOGGER = LoggerFactory.getLogger(MainProcess.class);
   public static final int MAX_TOTAL_ITEM = 150;

   // PRIVATE PARAMETER
   private final SaleParameters saleParameter;
   private final UserParameters userParameter;
   private final TimeParameters timeParameter;

   // Internal Parameters
   private final WebDriver webDriver = new FirefoxDriver();
   private PurchaseInfo purchaseInfo;

   public MainProcess() {
      super();
      saleParameter = null;
      userParameter = null;
      timeParameter = null;
      purchaseInfo = new PurchaseInfo();
   }

   public MainProcess(SaleParameters saleParameter, UserParameters userParameter, TimeParameters timeParameter) {
      super();
      this.saleParameter = saleParameter;
      this.userParameter = userParameter;
      this.timeParameter = timeParameter;
      purchaseInfo = new PurchaseInfo();
   }

   /**
    * Main logic here:<br/>
    * - login main page<br/>
    * - load page for sales<br/>
    * - load sub menu for one mark<br/>
    * - load item pages and add into shopping cart<br/>
    */
   public void process() {
      LOGGER.info("My dear master Ruochen LI, I am your personal VP assistant! The target is " + saleParameter.getExpectedMarkValue());
      SystemController.setAutoShutDown(timeParameter);
      Calendar startTime = MainPageLoader.loginMainPage(webDriver, timeParameter, userParameter);
      SalesPageLoader.loadSalesPage(webDriver, saleParameter, timeParameter);
      SubMenuPageLoader.loadSubMenuPage(webDriver, saleParameter, timeParameter);
      AddToCartPageLoader.loadAddToCartPage(webDriver, saleParameter, purchaseInfo);

      Calendar endTime = Calendar.getInstance();
      long timeDiffInSec = (endTime.getTime().getTime() - startTime.getTime().getTime()) / 1000 % 60;
      long timeDiffInMin = timeDiffInSec / 60;

      LOGGER.info("Dear master, I got mark:" + saleParameter.getExpectedMarkValue() + " " + purchaseInfo.toString());
      LOGGER.info("within:" + timeDiffInMin + "min" + timeDiffInSec + "sec");
   }

   @Override
   public void run() {
      process();
   }

}
