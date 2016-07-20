package vpCamping.process;

import java.util.Hashtable;
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
public final class SubMenuPageLoader {

   private static final Logger LOGGER = LoggerFactory.getLogger(SubMenuPageLoader.class);

   // SALE PAGE FOR ONE MARK
   static final String SUB_MENU_CLASS_TAG = "subMenuEV";
   public static final String FILTER_RESULT_CONTAINER = "filterResultContainer";
   public static final String DATA_VALUE = "data-value";

   /**
    * Load sub menu page
    */
   public static void loadSubMenuPage(WebDriver webDriver, SaleParameters saleParameter, TimeParameters timeParameter) {

      WebElement subMarkMenu = webDriver.findElement(By.className("open"));
      try {
         subMarkMenu = subMarkMenu.findElement(By.className(SUB_MENU_CLASS_TAG));
         String subMarkLink = getSubMenuLink(webDriver, saleParameter, subMarkMenu);
         webDriver.get(subMarkLink);
         filterTaille(webDriver, saleParameter);
      } catch (NoSuchElementException e) {
         LOGGER.info("There is no sub menu under this mark！");
      } catch (NotFindSubMenuLinkException e) {
         LOGGER.info(e.getMessage());
      }


   }

   private static void filterTaille(WebDriver webDriver, SaleParameters saleParameter) {
      List<String> expectedSizeList = saleParameter.getExpectedSizeList();
      List<WebElement> tailleBlocks = webDriver.findElement(By.className(FILTER_RESULT_CONTAINER)).findElements(By.className("detailBlocSize"));
      boolean first = true;
      StringBuilder target = new StringBuilder();
      for (WebElement tailleBlock : tailleBlocks) {
         WebElement sizeBlock = tailleBlock.findElement(By.tagName("a"));
         String size = sizeBlock.getAttribute("title").toUpperCase();
         if (expectedSizeList.contains(size)) {
            if (first) {
               target = new StringBuilder(sizeBlock.getAttribute(PageLoaderHelper.HYPER_REFERENCE_ATTRIBUTE_TAG));
               first = false;
            } else {
               target.append(';').append(sizeBlock.getAttribute(DATA_VALUE));
            }
         }
      }
      webDriver.get(target.toString());
   }

   /**
    * Get all sub menus
    *
    * @param markMenu Menu (HTML element)
    * @return link to a expected sub menu or null (no found)
    */
   private static String getSubMenuLink(WebDriver webDriver, SaleParameters saleParameter, WebElement markMenu) throws NotFindSubMenuLinkException {
      List<WebElement> markList = markMenu.findElements(By.tagName("li"));

      for (WebElement element : markList) {
         WebElement hrefLink;
         String markLink = webDriver.getCurrentUrl();
         try {
            hrefLink = element.findElement(By.tagName("a"));
            markLink = hrefLink.getAttribute(PageLoaderHelper.HYPER_REFERENCE_ATTRIBUTE_TAG);
         } catch (NoSuchElementException e) {
            //In this case, subMenu has already been selected, the current link is then the mark link
            hrefLink = element.findElement(By.tagName("span"));
         }

         String markNameText = hrefLink.getText().toUpperCase();
         for (String expectedSubMenu : saleParameter.getExpectedSubMenuList()) {
            String expectedSubMenuFormatted = PageLoaderHelper.replaceSpaceAndAsterisk(expectedSubMenu).toUpperCase();
             if (markNameText.matches(PageLoaderHelper.ANY_STRING + expectedSubMenuFormatted + PageLoaderHelper.ANY_STRING) ||
              markNameText.matches(PageLoaderHelper.ANY_STRING + expectedSubMenu.toUpperCase() + PageLoaderHelper.ANY_STRING)) {
               return markLink;
            }
         }

      }

      throw new NotFindSubMenuLinkException("There is no sub menu under this mark");
   }

   private static void generateMarkLinkHashMap(WebDriver webDriver, List<WebElement> markList, Hashtable<String, String> dictMarkLink) {
      for (WebElement element : markList) {
         WebElement hrefLink;
         String markLink = webDriver.getCurrentUrl();
         try {
            hrefLink = element.findElement(By.tagName("a"));
            markLink = hrefLink.getAttribute(PageLoaderHelper.HYPER_REFERENCE_ATTRIBUTE_TAG);
         } catch (NoSuchElementException e) {
            //In this case, subMenu has already been selected, the current link is then the mark link
            hrefLink = element.findElement(By.tagName("span"));
         }

         String markNameText = hrefLink.getText().toUpperCase();
         dictMarkLink.put(markNameText, markLink);
      }
   }

   private static final class NotFindSubMenuLinkException extends Exception {

      private NotFindSubMenuLinkException(String message) {
         super(message);
      }
   }
}
