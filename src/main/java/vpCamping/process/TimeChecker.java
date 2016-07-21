package vpCamping.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vpCamping.object.TimeParameters;

import java.util.Calendar;

/**
 * Created by ruli on 7/21/15.
 */
public final class TimeChecker {

   private static final Logger LOGGER = LoggerFactory.getLogger(TimeChecker.class);

   public static Calendar loadOnePageAtDefinedTime(TimeParameters timeParameter) {
      int startMonth = timeParameter.getStartMonth();
      int startDay = timeParameter.getStartDay();
      int startMinute = timeParameter.getStartMinute();
      int startHour = timeParameter.getStartHour();
      int refreshTime = timeParameter.getRefreshTime();
      Calendar now = Calendar.getInstance();
      boolean started = checkSaleStarted(now, startHour, startMinute, startMonth, startDay);
      try {
         while (!started) {
            now = Calendar.getInstance();
            started = checkSaleStarted(now, startHour, startMinute, startMonth, startDay);
            Thread.sleep(refreshTime * 1000);
         }
         Thread.sleep(timeParameter.getRefreshTime() * 1000);
      } catch (InterruptedException ignored) {
      } catch (Exception ignored) {
      }
      System.out.println("GO::" + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND));
      LOGGER.info("Time is up!");
      return now;
   }

   private static boolean checkSaleStarted(Calendar now, int startHour, int startMinute, int startMonth, int startDay) {
      if (((now.get(Calendar.MONTH) + 1 == startMonth && now.get(Calendar.DAY_OF_MONTH) == startDay) ||  (-1 == startMonth && -1 == startDay)) &&
        ((now.get(Calendar.HOUR_OF_DAY) == startHour && now.get(Calendar.MINUTE) >= startMinute) || now.get(Calendar.HOUR_OF_DAY) > startHour)) {
         return true;
      }
      return false;
   }
}
