/*
 * Time parameters
 */
package vpCamping.object;

import java.util.Calendar;

/**
 * @author LRC
 */
public class TimeParameters {

   private final int startHour;
   private final int startMinute;
   private final int refreshTime;
   private final int shutDownHour;
   private final int shutDownMinute;
   private final int startMonth;
   private final int startDay;
   private final boolean isShutDownActivated;

   public TimeParameters(Builder builder) {
      this.startDay = builder.startDay;
      this.startHour = builder.startHour;
      this.startMinute = builder.startMinute;
      this.refreshTime = builder.refreshTime;
      this.shutDownHour = builder.shutDownHour;
      this.startMonth = builder.startMonth;
      this.shutDownMinute = builder.shutDownMinute;
      this.isShutDownActivated = builder.isShutDownActivated;
   }

   /**
    * @return the startHour
    */
   public int getStartHour() {
      return startHour;
   }

   /**
    * @return the startMinute
    */
   public int getStartMinute() {
      return startMinute;
   }

   /**
    * @return the refreshTime
    */
   public int getRefreshTime() {
      return refreshTime;
   }

   /**
    * @return the shutDownHour
    */
   public int getShutDownHour() {
      return shutDownHour;
   }

   /**
    * @return the shutDownMinute
    */
   public int getShutDownMinute() {
      return shutDownMinute;
   }

   public int getStartDay() {
      return startDay;
   }

   public int getStartMonth() {
      return startMonth;
   }

   public boolean isShutDownActivated(){
      return isShutDownActivated;
   }

   public static final class Builder {

      private static final Calendar CURRENT_TIME = Calendar.getInstance();
      private static final int START_MONTH = CURRENT_TIME.get(Calendar.MONTH) + 1;
      private static final int START_DAY = 24;

      private int startHour;
      private int startMinute;
      private int refreshTime;
      private int shutDownHour;
      private int shutDownMinute;
      private int startMonth = -1;
      private int startDay = -1;
//      private int startMonth = 1;
//      private int startDay = 10;
      private boolean isShutDownActivated;

      public Builder() {

      }

      public Builder withStartHour(int startHour) {
         this.startHour = startHour;
         return this;
      }

      public Builder withStartMinute(int startMinute) {
         this.startMinute = startMinute;
         return this;
      }

      public Builder withRefreshTime(int refreshTime) {
         this.refreshTime = refreshTime;
         return this;
      }

      public Builder withShutDownHour(int shutDownHour) {
         this.shutDownHour = shutDownHour;
         return this;
      }

      public Builder withShutDownMinute(int shutDownMinute) {
         this.shutDownMinute = shutDownMinute;
         return this;
      }

      public Builder withStartMonth(int startMonth) {
         this.startMonth = startMonth;
         return this;
      }

      public Builder withStartDay(int startDay) {
         this.startDay = startDay;
         return this;
      }

      public Builder withIsShutDownActivated(boolean isShutDownActivated) {
         this.isShutDownActivated = isShutDownActivated;
         return this;
      }

      public TimeParameters build() {
         return new TimeParameters(this);
      }

   }
}
