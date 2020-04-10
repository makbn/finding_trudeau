package io.github.makbn.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This utils is used for converting different form of date from different providers
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class DateUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat cnnDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static SimpleDateFormat cnnDateFormatSec = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


    public static Date convert(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    public static String convert(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date convertCNN(String date) throws ParseException {
        if (date == null)
            return null;
        try {
            return cnnDateFormat.parse(date);
        } catch (ParseException e) {
            /*some of articles have different date format*/
            return cnnDateFormatSec.parse(date);
        }
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
