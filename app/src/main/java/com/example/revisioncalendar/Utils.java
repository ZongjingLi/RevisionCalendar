package com.example.revisioncalendar;

import com.example.revisioncalendar.DataWrapper.Activity;

import java.util.ArrayList;

public class Utils {
    public static int compareDate(String date1, String date2) {
        return 1;
    }



    public static Activity parseString(String string) throws IllegalAccessException {
        String[] comps = string.split("\\|");

        if (comps.length != 5) {
            throw new IllegalAccessException(String.format("%s not a valid string", string));
        }
        String title = comps[0];
        String type = comps[1];
        String location = comps[2];
        String start_date = comps[3];
        String end_date = comps[4];

        return new Activity(title, type, location, start_date, end_date);
    }

    public static ArrayList<Activity> filterType(ArrayList<Activity> events, String value) {
        ArrayList<Activity> outputs = new ArrayList<Activity>();
        for (int i=0; i<events.size();  i++) {
            Activity event = events.get(i);
            if (event.type.equals(value) || value.equals("All")) {
                outputs.add(event);
            }
        }
        return outputs;
    }

    public static ArrayList<Activity> filterEndDate(ArrayList<Activity> events, String value) {
        ArrayList<Activity> outputs = new ArrayList<Activity>();
        for (int i=0; i<events.size();  i++) {
            Activity event = events.get(i);
            //System.out.println(event.endDate);
            //System.out.println(value);
            if (event.endDate.equals(value)) {
                outputs.add(event);
            }
        }
        return outputs;
    }

    public static Boolean validName(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        String regex = "^[a-zA-Z][a-zA-Z\\s]*$";

        // 使用正则表达式检查字符串是否符合规范
        return true;
    }

    public static Boolean validTime(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        String regex = "^\\d{1,2}-\\d{1,2}-\\d{4}$";

        return str.matches(regex);

    }

    public String activity2String() {
        return "";
    }
}
