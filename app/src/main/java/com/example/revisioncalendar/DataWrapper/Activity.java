package com.example.revisioncalendar.DataWrapper;

public class Activity {
    public String title;
    public String type;
    public String location;
    public String startDate;
    public String endDate;

    public Activity(String title, String type, String location, String start_date, String end_date) {
        this.title = title;
        this.type = type;
        this.location = location;
        this.startDate = start_date;
        this.endDate = end_date;
    }

    public String getTitle() {return title;}
    public String getType() {return type;}
    public String getLocation() {return location;}

    public String toString() {
        return this.title;
    }

}
