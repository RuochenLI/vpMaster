package vpCamping.process;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vpCamping.object.SaleParameters;
import vpCamping.object.TimeParameters;

/**
 * Created by ruli on 7/21/15.
 */
public final class SalesPageLoader {

   private static final String SALE_PAGE_LINK_PREFIX = "http://fr.vente-privee.com/vp4/Home/Handlers/OperationAccess.ashx?operationid=";
   private static final String MENU_CLASS_TAG = "obj_menuEV";
   private static final Logger LOGGER = LoggerFactory.getLogger(SalesPageLoader.class);

   /**
    * Open a specific sales, if it has not been opened yet, re-try in 3 seconds
    */
   public static void loadSalesPage(WebDriver webDriver, SaleParameters saleParameter, TimeParameters timeParameter) {
      String urlSale = SALE_PAGE_LINK_PREFIX + saleParameter.getSaleIdValue();
      boolean isSuccess = false;
      while (!isSuccess) {
         webDriver.get(urlSale);
         String markLink;
         try {
            WebElement markMenu = webDriver.findElement(By.className(MENU_CLASS_TAG));
            markLink = getMarkLink(markMenu, saleParameter.getExpectedMarkValue());
            if (markLink != null) {
               isSuccess = openLink(webDriver, markLink);
            }
         } catch (Exception e) {
            LOGGER.info("Private sales is not open to public yet.");
            TimeChecker.loadOnePageAtDefinedTime(timeParameter);
         }
      }
   }

   /**
    * Get a link for a expected mark
    *
    * @param markMenu menu list (HTML element)
    * @param expectedString expected mark (all upper case)
    * @return link or null (no found)
    */
   private static String getMarkLink(WebElement markMenu, String expectedString) {
      List<WebElement> markList = markMenu.findElements(By.tagName(PageLoaderHelper.HTML_LIST_TAG));
      for (int i = 1; i < markList.size(); i++) { // Only from the 2nd element
         WebElement element = markList.get(i);
         try {
            WebElement hrefLink = element.findElement(By.tagName(PageLoaderHelper.A_TAG));
            try {
               WebElement markNameElement = hrefLink.findElement(By.tagName(PageLoaderHelper.SPAN_TAG));
               String markNameText = markNameElement.getText().toUpperCase();
               LOGGER.info("Found marks " + markNameText);
               if (markNameText.matches(PageLoaderHelper.ANY_STRING + expectedString.toUpperCase() + PageLoaderHelper.ANY_STRING)) {
                  return hrefLink.getAttribute(PageLoaderHelper.HYPER_REFERENCE_ATTRIBUTE_TAG);
               }
            } catch (NoSuchElementException e) {
               LOGGER.info("This item is not one of marks");
            }

         } catch (NoSuchElementException e) {
            LOGGER.info("I didn't find this marks");
         }

      }
      return null;
   }

   /**
    * Open a given link
    *
    * @param markLink link to open
    * @return if it's opened successully
    */
   public static boolean openLink(WebDriver webDriver, String markLink) {
      boolean isSuccess = false;
      if (markLink != null) {
         webDriver.get(markLink);
         isSuccess = true;
      }
      return isSuccess;
   }
}
