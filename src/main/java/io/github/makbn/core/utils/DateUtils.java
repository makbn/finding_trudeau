package io.github.makbn.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class DateUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date convert(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    public static String convert(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date getPreviousYear() {
        Calendar prevYear = Calendar.getInstance();
        prevYear.add(Calendar.YEAR, -1);
        return prevYear.getTime();
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }
}
