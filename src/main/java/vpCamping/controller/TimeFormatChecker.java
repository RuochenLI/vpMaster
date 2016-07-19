package vpCamping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ruli on 7/27/15.
 */
public final class TimeFormatChecker {
   private final static Logger LOGGER = LoggerFactory.getLogger(TimeFormatChecker.class);

   public static boolean checkTimeFormat(int startHourInt, int startMinuteInt, int refreshTimeInt, int endHourInt, int endMinuteInt, javax.swing.JLabel informationLabel) {
      boolean success = true;
      if ((startHourInt > 23) || (startHourInt < 0)) {
         informationLabel.setText("Input hour is not correct！");
         LOGGER.info("Input hour is not correct！");
         success = false;
      }
      if ((startMinuteInt > 59) || (startMinuteInt < 0)) {
         informationLabel.setText("Input minute is not correct！");
         LOGGER.info("Input minute is not correct！");
         success = false;
      }
      if ((endHourInt > 23) || (endHourInt < -1)) {
         informationLabel.setText("Input hour is not correct！");
         LOGGER.info("Input hour is not correct！");
         success = false;
      }
      if ((endMinuteInt > 59) || (endMinuteInt < -1)) {
         informationLabel.setText("Input minute is not correct！");
         LOGGER.info("Input minute is not correct！");
         success = false;
      }
      if (refreshTimeInt <= 2) {
         informationLabel.setText("To avoid website overloaded, please input an interval time more than 3 seconds！");
         LOGGER.info("To avoid website overloaded, please input an interval time more than 3 seconds！");
         success = false;
      }
      return success;
   }

}
