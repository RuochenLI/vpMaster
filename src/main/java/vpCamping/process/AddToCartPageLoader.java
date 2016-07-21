package vpCamping.process;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vpCamping.object.SaleParameters;

import java.util.List;

/**
 * Created by ruli on 7/21/15.
 */
public final class AddToCartPageLoader {
   private static final Logger LOGGER = LoggerFactory.getLogger(AddToCartPageLoader.class);

   // ITEMS PAGE
   private static final String EXPRESS_BUY_CLASS_TAG = "infoExpressBt";

   // EXPRESS BUY PAGE
   private static final String PRODUCE_ID_TAG = "withStock";
   private static final String CONFIRM_BUY_BUTTON_TAG = "addToCartLink";
   private static final String DISPONIBLE_TAG = "DISP";

   /**
    * Load item page and add a expected size (all upper case) into shopping
    * cart
    */
   public static void loadAddToCartPage(WebDriver webDriver, SaleParameters saleParameter, PurchaseInfo purchaseInfo) {
      JavascriptExecutor js = (JavascriptExecutor) webDriver;
      for (int i = 0; i < saleParameter.getScrollPageFrequence(); i++) {
         js.executeScript("javascript:window.scrollBy(800,800)");
      }
      List<WebElement> boutonLink = webDriver.findElements(By.className(EXPRESS_BUY_CLASS_TAG));
      int startIndex = boutonLink.size();
      String[] shortLinks = new String[boutonLink.size()];
      int actualIndex = 0;
      for (int j = startIndex - 1; j >= 0; j--) {
         actualIndex = addShortLinkIntoTable(boutonLink, j, shortLinks, actualIndex);
      }
      for (String shortLink : shortLinks) {
         addItemIntoCart(shortLink, webDriver, saleParameter, purchaseInfo);
      }
   }

   /**
    * Add all items short link into table for futur operations
    *
    * @param boutonLink bouton link to parse
    * @param i element index
    * @param shortLinks table to save
    * @param actualIndex actual index in the table
    * @return
    */
   private static int addShortLinkIntoTable(List<WebElement> boutonLink, int i, String[] shortLinks, int actualIndex) {
      WebElement bouton = boutonLink.get(i);
      String shortLink = bouton.getAttribute("href");
      shortLinks[actualIndex++] = shortLink;

      return actualIndex;
   }

   /**
    * Look up the expected size in the given item and add it into shopping cart
    *
    * @param link
    * @param purchaseInfo
    */
   private static int addItemIntoCart(String link, WebDriver webDriver, SaleParameters saleParameter, PurchaseInfo purchaseInfo) {
      int temp = 0;
      webDriver.get(link);
      Select selectItems = getProductModelSelection(webDriver);
      double value = 0;
      double originalValue = 0;
      try {
         String text = webDriver.findElement(By.id("vpPriceBlock")).findElement(By.className("priceSpan")).getText();
         if (text != null && !text.isEmpty()) {
         text = text.substring(0, text.length() - 2);//€ is a special caracter, it depends sys and sometimes it doesn't work. so better to use substring here
         text = text.replace(",", ".");
            value = Double.valueOf(text);
         }
         text = webDriver.findElement(By.id("advisedPriceBlock")).findElement(By.className("advisedPriceValue")).getText();;
         if (text != null && !text.isEmpty()) {
            text = text.substring(0, text.length() - 2);//€ is a special caracter, it depends sys and sometimes it doesn't work. so better to use substring here
            text = text.replace(",", ".");
            originalValue = Double.valueOf(text);
         }

      } catch (NoSuchElementException e) {
         LOGGER.info("Item price not found", e);
      }
      try {
         WebElement confirmBouton = webDriver.findElement(By.id(CONFIRM_BUY_BUTTON_TAG));
         if (selectItems != null) {
            getRightSizeElement(saleParameter, selectItems, confirmBouton, purchaseInfo);
         } else {
            try {
               confirmBouton.click();
            } catch (Exception e) {
               LOGGER.error(e.getMessage());
            }

         }

      } catch (NoSuchElementException  e) {
         LOGGER.info("Sorry, I didn't find confirm button");
      }

      return temp;
   }

   private static void getRightSizeElement(SaleParameters saleParameter, Select selectItems, WebElement confirmBouton, PurchaseInfo purchaseInfo) {
      List<WebElement> options = selectItems.getOptions();
      for (int optionIndex = 1; optionIndex < options.size(); optionIndex++) {
         boolean isFound = searchForRightSize(saleParameter, options, optionIndex);
         if (isFound && purchaseInfo.getNbItem() < MainProcess.MAX_TOTAL_ITEM) {
            selectItems.selectByIndex(optionIndex);
            try {
               confirmBouton.click();
               purchaseInfo.incrementItem();
            } catch (Exception e) {
               LOGGER.error(e.getMessage());
            }

         }
      }
   }

   private static boolean searchForRightSize(SaleParameters saleParameter, List<WebElement> options, int i) {
      WebElement option = options.get(i);
      String size = option.getText().toUpperCase();
      for (String expectedSize : saleParameter.getExpectedSizeList()) {
         String expectedSizeFormatted = PageLoaderHelper.replaceSpaceAndAsterisk(expectedSize);
         if (size.matches(PageLoaderHelper.ANY_STRING + expectedSizeFormatted + PageLoaderHelper.ANY_STRING + DISPONIBLE_TAG + PageLoaderHelper.ANY_STRING)) {
            return true;
         }
      }
      return false;
   }

   private static Select getProductModelSelection(WebDriver webDriver) {
      WebElement productId = webDriver.findElement(By.id(PRODUCE_ID_TAG));
      if (productId != null) {
         try {
            return new Select(productId.findElement(By.id("model")));
         } catch (Exception e) {
            LOGGER.info("The item is unique size！");
            return null;
         }
      }

      return null;
   }
}
