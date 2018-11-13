package com.java.xingfu.service.joda;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author 掘金
 * @description
 * @date 2018/11/8
 */
public class JodaTest {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static DateTimeFormatter format = DateTimeFormat.forPattern("yyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        instant();
        dateTimeZone();
        parse();
        plusDays();
        dayToNewYear();
        dateTimeZone2();
        duration();
        daysBetween();
    }

    public static void daysBetween() {
        System.out.println(new DateTime(2018,11,1,10,0,0,0).millisOfDay().withMaximumValue().toString(format));
        int i= Days.daysBetween(new DateTime(2018,11,1,12,0,0), new DateTime(2018,11,2,10,0,0).millisOfDay().withMaximumValue()).getDays();
        System.out.println(i);
    }

    private static void duration() {
        DateTime begin = new DateTime("2018-11-01");
        DateTime end = new DateTime("2018-11-02");

        Duration d= new Duration(begin,end);
        System.out.println(d.getStandardDays());

        Period p = new Period(begin,end, PeriodType.days());
        System.out.println(p.getDays());

        Interval interval = new Interval(begin,end);
        System.out.println(interval.contains(new DateTime("2015-12-23")));
    }

    private static void dateTimeZone2() {
        DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));
        DateTime dt1 = new DateTime();
        System.out.println(dt1.toString(format));

        DateTime dt2 = new DateTime(DateTimeZone.UTC);
        System.out.println(dt2.toString(format));
    }

    private static void dayToNewYear() {
        DateTime dateTime = new DateTime();
        DateTime dateTime1 = dateTime.plusYears(1).withDayOfYear(1);
        System.out.println(dateTime1.toString(format));
        System.out.println(Days.daysBetween(dateTime,dateTime1).getDays());
    }

    private static void plusDays() {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.plusDays(90).toString(format));
    }

    private static void parse() {

        DateTime dateTime1 = DateTime.parse("2015-12-23 12:34:34",format);
        System.out.println(dateTime1.toString(format));

    }

    private static void instant() {
        Instant instant = new Instant();
        System.out.println(instant);
        DateTime dt = new Instant().toDateTime();
        System.out.println(sdf.format(dt.toDate()));
    }

    private static void dateTimeZone() {

        DateTime dateTime = new DateTime();
        Date dt = new DateTime().withZone(DateTimeZone.forID("America/New_York")).toLocalDateTime().toDate();
        System.out.println(sdf.format(dt));
        Date dt2 = new DateTime().toLocalDate().toDate();
        System.out.println(sdf.format(dt2));

        System.out.println(sdf.format(dateTime.toDate()));
    }
}
