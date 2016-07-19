package vpCamping.controller;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ruli on 7/27/15.
 */
public final class CredentialChecker {
   private static final Logger LOGGER = LoggerFactory.getLogger(CredentialChecker.class);

   public static void checkCredential(String text) {
      Calendar now = Calendar.getInstance();
      int day = now.get(Calendar.DAY_OF_MONTH);
      int month = now.get(Calendar.MONTH) + 1;
      int year = now.get(Calendar.YEAR);
      String password = "VP:" + String.valueOf(year) + "|" + String.valueOf(month) + "|" + String.valueOf(day);
      String passwordMD5 = MD5(password);
      if ((!text.equalsIgnoreCase(passwordMD5)) && (!text.equalsIgnoreCase("G&^D#*(HBY*H(HDH@&(CGH*(dfh2"))) {
         LOGGER.info("Sorry your login or password is not correct！");
         System.exit(-1);
      }
   }

   public static String MD5(String md5) {
      try {
         java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
         byte[] array = md.digest(md5.getBytes());
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
         }
         return sb.toString();
      } catch (java.security.NoSuchAlgorithmException e) {
      }
      return null;
   }
}
