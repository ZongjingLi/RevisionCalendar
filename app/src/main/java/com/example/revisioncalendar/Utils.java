package com.example.revisioncalendar;

import com.example.revisioncalendar.DataWrapper.Activity;

public class Utils {
    public static int compareDate(String date1, String date2) {
        return 1;
    }

    public Activity parseString(String string) throws IllegalAccessException {
        String[] comps = string.split("|");
        if (comps.length != 5) {
            throw new IllegalAccessException(String.format("%s not a valid string", string));
        }
        String title = comps[0];
        String type = comps[1];
        String location = comps[2];
        String start_date = comps[3];
        String end_date = comps[4];
        System.out.println(title);
        System.out.println(end_date);
        return new Activity(title, type, location, start_date, end_date);
    }

    public String activity2String() {

    }
}
