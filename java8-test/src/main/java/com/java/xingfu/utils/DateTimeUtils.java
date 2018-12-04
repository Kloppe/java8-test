package com.java.xingfu.utils;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * @author 掘金
 * @date 2018/11/21
 * @desc
 */
public class DateTimeUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String toString(Date date){
        return toString(new DateTime(date));
    }

    public static String toString(DateTime dateTime) {
        return dateTime.toString(DEFAULT_DATE_FORMAT);
    }

    public static Date parse(String date){
        return parseDateTime(date).toDate();
    }

    private static DateTime parseDateTime(String date) {
        return DateTime.parse(date,DateTimeFormat.forPattern(DEFAULT_DATE_FORMAT));
    }

}
