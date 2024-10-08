package com.android.demophone.utils;

import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    public static final String ASIA_KOLKATA = "Asia/Kolkata";
    public static final String INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String OUTPUT_FORMAT = "dd/MM/yy hh:mm:ss a";

    /**
     * Check the input date and format accordingly
     *
     * @param date
     * @return
     */
    public static String checkAndFormatDateString(String date) {
        if (TextUtils.isEmpty(date)) {
            return "NA";
        } else {
            return formattedDateString(date);
        }
    }

    /**
     * This method converts date string into formatted string
     *
     * @param dateInString
     * @return
     */
    private static String formattedDateString(String dateInString) {

        String formatted = null;
        try {
            // Convert date string into 'Date' object
            SimpleDateFormat input = new SimpleDateFormat(INPUT_FORMAT);
            input.setTimeZone(TimeZone.getTimeZone(ASIA_KOLKATA));

            Date date = null;
            try {
                date = input.parse(dateInString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Convert 'Date' object according to output format
            SimpleDateFormat output = new SimpleDateFormat(OUTPUT_FORMAT);
            formatted = output.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return formatted;
    }

    /**
     * This method check the current time is in between the given time range or not.
     *
     * @param fromTime
     * @param toTime
     * @return true if current time is in given range, otherwise false
     */
    public static boolean checkCurrentTimeInBetweenDates(String fromTime, String toTime) {
        Calendar calFrom = getCalenderFromTimeString(fromTime);
        Calendar calTo = getCalenderFromTimeString(toTime);
        Calendar calCurrent = getCalenderFromTimeString(getCurrentHourMin());

        Date res = calCurrent.getTime();

        //check whether the current time is between from and to
        return res.after(calFrom.getTime()) && res.before(calTo.getTime());
    }

    /**
     * Get the calender object from time string.
     *
     * @param timeInHourMin
     * @return
     */
    private static Calendar getCalenderFromTimeString(String timeInHourMin) {
        Calendar calendar = null;
        try {
            Date time = new SimpleDateFormat("HH:mm").parse(timeInHourMin);
            calendar = Calendar.getInstance();
            calendar.setTime(time);
            calendar.add(Calendar.DATE, 1);
        } catch (Exception pe) {
            pe.printStackTrace();
        }

        return calendar;
    }

    /**
     * This method returns the current time in HH:mm format
     *
     * @return
     */
    private static String getCurrentHourMin() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return dateFormatter.format(calendar.getTime());
    }


    public static String segregateNextInteractionDateTime(String dateTimeString) {
        if(TextUtils.isEmpty(dateTimeString)) {
            return "";
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            // Parse the date-time string to a ZonedDateTime object
            ZonedDateTime zonedDateTime = null;
            zonedDateTime = ZonedDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME);

            // Extract the date component
            LocalDate date = zonedDateTime.toLocalDate();

            // Extract the time component
            LocalTime time = zonedDateTime.toLocalTime();

            // Define formatters for date and time
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            // Format the date and time
            String formattedDate = date.format(dateFormatter);
            String formattedTime = time.format(timeFormatter);

            // Print the formatted date and time
            System.out.println("Date: " + formattedDate);
            System.out.println("Time: " + formattedTime);

            return (formattedDate + "|" + formattedTime);
        } else {
            return "";
        }
    }
}
