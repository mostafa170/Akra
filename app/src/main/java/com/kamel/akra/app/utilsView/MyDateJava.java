package com.kamel.akra.app.utilsView;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyDateJava {
   public static Date getDate(String date, String format, Locale fromLocale){
      SimpleDateFormat dateFormat = new SimpleDateFormat(format, fromLocale);
      try {
         return dateFormat.parse(date);
      } catch (Exception e) {
         Log.i("XXX", "getDate: " + e.getMessage());
         return new Date();
      }
   }

   public static String millisecondsToCountDownString(long milliseconds) {
      long totalSecs = milliseconds/1000;
      long hours = (totalSecs / 3600);
      long mins = (totalSecs / 60) % 60;
      long secs = totalSecs % 60;
      String minsString = (mins == 0)
              ? "00"
              : ((mins < 10)
              ? "0" + mins
              : "" + mins);
      String secsString = (secs == 0)
              ? "00"
              : ((secs < 10)
              ? "0" + secs
              : "" + secs);
      if (hours > 0)
         return hours + ":" + minsString + ":" + secsString;
      else if (mins > 0)
         return "00:" + mins + ":" + secsString;
      else return "00:00:" + secsString;
   }

}
