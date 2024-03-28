package com.example.revisioncalendar;

import com.example.revisioncalendar.DataWrapper.Activity;

import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

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

    public static int compareDate(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            String[] d1 = date1.split("-");
            String[] d2 = date2.split("-");
            int yearDiff = Integer.parseInt(d1[2]) - Integer.parseInt(d2[2]);
            int monthDiff = Integer.parseInt(d1[1]) - Integer.parseInt(d2[1]);
            int dayDiff = Integer.parseInt(d1[0]) - Integer.parseInt(d2[0]);
            if (yearDiff < 0) {
                return 1;
            } else if (yearDiff > 0) {
                return -1;
            } else if (monthDiff < 0) {
                return 1;
            } else if (monthDiff > 0) {
                return -1;
            } else if (dayDiff < 0) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(String.format("%s %s",date1, date2));
            e.printStackTrace();

            // 如果日期格式不正确，可以根据需要进行异常处理
        }
        return 1;
    }

    public static ArrayList<Activity> sortByDate(ArrayList<Activity> events) {
        ArrayList<Activity> outputs = new ArrayList<Activity>();
        int N = events.size();
        for (int i = 0; i < N; i++) {
            int min = 0;
            String minDate = events.get(0).endDate;
            for (int j = 1; j < events.size(); j++) {
                Activity event = events.get(j);
                if (compareDate(event.endDate,minDate) >= 0) {
                    min = j;
                    minDate = event.endDate;
                }
            }
            System.out.println(minDate);
            outputs.add(events.get(min));
            events.remove(min);
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
