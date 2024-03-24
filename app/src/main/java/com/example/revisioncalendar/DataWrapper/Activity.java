package com.example.revisioncalendar.DataWrapper;

public class Activity {
    private String title;
    private String type;
    private String location;
    private String startDate;
    private String endDate;

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
