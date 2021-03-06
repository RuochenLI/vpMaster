package vpCamping.launcher;

import vpCamping.controller.MainController;

/**
 * Created by ruli on 7/18/2016.
 */
public class VPMasterLauncher {
   private static final String CONFIG = "config";
   private static final String EXTENSION = ".properties";
   private static final int TOTAL = 5;

   public static void main(String args[]) {

      for (int i = 0; i < TOTAL; i++) {
         final int index = i;
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               MainController.start(CONFIG + index + EXTENSION);
            }
         });
      }
   }
}
