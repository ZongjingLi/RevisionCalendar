package com.example.revisioncalendar.DataWrapper;

public class Activity {
    private String title;
    private String type;
    private String location;

    public Activity(String title, String type, String location) {
        this.title = title;
    }

    public String getTitle() {return title;}
    public String getType() {return type;}
    public String getLocation() {return location;}

    public String toString() {
        return this.title;
    }

}
