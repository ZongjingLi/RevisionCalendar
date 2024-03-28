package com.example.revisioncalendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;


import android.content.ContentValues;

import com.example.revisioncalendar.DataWrapper.Activity;
import com.google.android.material.snackbar.Snackbar;

import java.sql.SQLInput;
import java.util.ArrayList;
import android.content.Context;

public class ItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<Activity> activityList;
    LayoutInflater inflter;
    Button button;
    public ItemAdapter(Context applicationContext, ArrayList<Activity> ActivityList) {
        this.context = applicationContext;
        this.activityList = ActivityList;
        this.inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return activityList.size();
    }
    @Override
    public Object getItem(int i) {
        return activityList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    PopupWindow popupWindow = null;
    public View getView(int i, View view, ViewGroup viewGroup) {
        int fontSize = 12;
        view = inflter.inflate(R.layout.event_item, null);
        // var popupWindow: PopupWindow? = null

        TextView activityName = view.findViewById(R.id.objTitle);
        TextView activityType = view.findViewById(R.id.eventType);
        TextView activityTime = view.findViewById(R.id.eventTime);
        TextView activityLocation = view.findViewById(R.id.eventLocation);

        activityName.setText(activityList.get(i).getTitle());
        activityType.setText(activityList.get(i).getType());
        activityLocation.setText(activityList.get(i).getLocation());
        activityTime.setText(activityList.get(i).endDate);

        button = view.findViewById(R.id.closeButton);
        View finalView = view;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click


                //DataBaseHandle db = new DataBaseHandle(Context, null);

                String content = String.format("%s|%s|%s|%s|%s",
                        activityList.get(i).getTitle(),
                        activityList.get(i).type,
                        activityList.get(i).location,
                        activityList.get(i).startDate,
                        activityList.get(i).endDate
                        );
                System.out.println("Deleted");

            }
        });

        return view;
    }
}
