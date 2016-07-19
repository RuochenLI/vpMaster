package vpCamping.process;

/**
 * Created by ruli on 7/21/15.
 */
public final class PageLoaderHelper {

   private static final String ANT_STRING_TO_REPLACE_IN_REG_EXP = "([\\\\s\\\\S]*)";

   public static final String ANY_STRING = "([\\s\\S]*)";
   public static final String HYPER_REFERENCE_ATTRIBUTE_TAG = "href";
   public static final String HTML_LIST_TAG = "li";
   public static final String A_TAG = "a";
   public static final String SPAN_TAG = "span";

   public static String replaceSpaceAndAsterisk(String expected) {
      String expectedFormatted = expected.replaceAll(" ", ANT_STRING_TO_REPLACE_IN_REG_EXP);
      expectedFormatted = expectedFormatted.replace("*", ANT_STRING_TO_REPLACE_IN_REG_EXP);
      return expectedFormatted;
   }
}
