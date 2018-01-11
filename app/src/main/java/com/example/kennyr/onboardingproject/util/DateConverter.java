package com.example.kennyr.onboardingproject.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    public static String dateToMonthDayYearLong(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        return dateFormat.format(date);
    }

    public static String dateToMonthDayYearShort(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MMM/dd/yy", Locale.US);
        return dateFormat.format(date);
    }

    public static Date dateFromMonthDayYearLong(String date) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date dateFromMonthDayYearShort(String date) {
        DateFormat dateFormat = new SimpleDateFormat("MMM/dd/yy", Locale.US);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
