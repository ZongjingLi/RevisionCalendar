package com.example.revisioncalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.revisioncalendar.DataWrapper.Activity;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<Activity> activityList;
    LayoutInflater inflter;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        int fontSize = 12;
        view = inflter.inflate(R.layout.event_item, null);

        TextView activityName = view.findViewById(R.id.objTitle);
        TextView activityType = view.findViewById(R.id.eventType);
        TextView activityTime = view.findViewById(R.id.eventTime);
        TextView activityLocation = view.findViewById(R.id.eventLocation);

        activityName.setText(activityList.get(i).getTitle());
        activityType.setText(activityList.get(i).getType());
        activityLocation.setText(activityList.get(i).getLocation());
        activityTime.setText(activityList.get(i).endDate);

        return view;
    }
}
