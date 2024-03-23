package com.example.revisioncalendar.DataWrapper;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.revisioncalendar.R;

import java.time.LocalTime;
import java.util.ArrayList;

public class ActivityAdapter extends BaseAdapter {
    Context context;
    ArrayList<Activity> activityList;
    LayoutInflater inflter;
    public ActivityAdapter(Context applicationContext, ArrayList<Activity> ActivityList) {
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
        int fontSize = 18;
        view = inflter.inflate(R.layout.event_item, null);

        TextView activityName = view.findViewById(R.id.objTitle);
        TextView activitType = view.findViewById(R.id.eventType);
        TextView activityTime = view.findViewById(R.id.eventTime);
        TextView activityLocation = view.findViewById(R.id.eventLocation);

        activityName.setText(activityList.get(i).getTitle());

        return view;
    }
}
