/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpCamping.process;

import vpCamping.object.TimeParameters;

import java.io.IOException;

/**
 *
 * @author LRC
 */
public class SystemController {

    final static String TASK_NAME = "VPMasterAutoShutDown";
    
    public static void setAutoShutDown(TimeParameters timeParameter) {
        if (timeParameter.isShutDownActivated()) {
            int shutDownHour = timeParameter.getShutDownHour();
            int shutDownMinute = timeParameter.getShutDownMinute();
            String shutDownHourString = String.valueOf(shutDownHour);
            String shutDownMinuteString = String.valueOf(shutDownMinute);

            if (shutDownHourString.length() == 1) {
                shutDownHourString = "0" + shutDownHourString;
            }
            if (shutDownMinuteString.length() == 1) {
                shutDownMinuteString = "0" + shutDownMinuteString;
            }
            if ((shutDownHour > -1) && (shutDownMinute > -1) && timeParameter.isShutDownActivated()) {
                shutDownProcess(shutDownHour, shutDownMinute, shutDownHourString, shutDownMinuteString);
            } else {
                shutDownUnsetProcess();
            }
        }

    }

   private static void shutDownUnsetProcess() {
      String cmd = "schtasks /delete /tn "+TASK_NAME+" /F";
      System.out.println("SHUT DOWN UNSET : SUCCESSFUL");
      Runtime runTime = Runtime.getRuntime();
      try {
          runTime.exec(cmd);
      } catch (IOException ex) {
          System.out.println("SHUT DOWN UNSET : FAILED\n" + ex);
      }
   }

   private static void shutDownProcess(long shutDownHour, long shutDownMinute, String shutDownHourString, String shutDownMinuteString) {
      String cmd = TASK_NAME + " shut down at " + shutDownHourString + ":" + shutDownMinuteString;
      System.out.println("SHUT DOWN SET AT: " +cmd + " "+ shutDownHour + ":" + shutDownMinute);
      Runtime runTime = Runtime.getRuntime();
      try {
          runTime.exec(cmd);
      } catch (IOException ex) {
          System.out.println("SHUT DOWN SET : FAILED\n" + ex);
      }
   }
}
