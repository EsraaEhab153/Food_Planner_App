package com.example.foodplannerapp.HelperClasses;

import java.util.Calendar;

public class DateUtils {

    public static long getStartOfWeek(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);

        // Adjust to Saturday as start
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int diff = Calendar.SATURDAY - dayOfWeek;
        if (diff > 0) diff -= 7; // لو اليوم قبل السبت
        cal.add(Calendar.DAY_OF_MONTH, diff);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }

    public static long getEndOfWeek(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);

        // Adjust to Friday as end
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int diff = Calendar.FRIDAY - dayOfWeek;
        if (diff < 0) diff += 7; // لو اليوم بعد الجمعة
        cal.add(Calendar.DAY_OF_MONTH, diff);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTimeInMillis();
    }

}

