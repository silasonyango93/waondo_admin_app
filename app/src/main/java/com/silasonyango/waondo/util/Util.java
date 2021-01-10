package com.silasonyango.waondo.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
    public static String formatToCommaSeperatedValue(double amount) {
        if(amount == 0){
            return "00.00";
        }else {
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            return formatter.format(amount);
        }


    }

    public static String getToday() {
        DateFormat stringDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = Calendar.getInstance().getTime();
        return stringDateFormat.format(currentTime);
    }

    public static String convertToUserFriendlyDate(String yourDateString,String yourDateFormat) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(yourDateFormat);
        DateFormat stringDateFormat = new SimpleDateFormat("E, MMM dd yyyy");
        try {
            date = formatter.parse(yourDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return stringDateFormat.format(date);
    }
}
