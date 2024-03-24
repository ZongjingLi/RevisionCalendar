package com.example.revisioncalendar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revisioncalendar.DataWrapper.Activity;
import com.example.revisioncalendar.DataWrapper.EventListAdapter

class ToDoListActivity : ComponentActivity() {
    var eventsData : ArrayList<Activity>? = null
    var backButton : Button? = null;
    var addEventButton : Button? = null;
    var events = listOf(
        Activity("ISY4699", "Lecture", "TBA", "23-3-2024", "23-3-2024"),
    );
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_events);
        setButtons();

        setEventsData()
        val adapter = EventListAdapter(eventsData)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    @SuppressLint("Range")
    fun setEventsData() {
        eventsData = ArrayList<Activity>();
        val db = DataBaseHandle(this, null)

        val cursor = db.getCourse()
        cursor!!.moveToFirst()

        while(cursor.moveToNext()){
            var title = cursor.getString(cursor.getColumnIndex(DataBaseHandle.NAME_COl));

            println(title);
            eventsData!!.add(Activity(title, "Lecture" , "Scourge", "25-3-2022", "25-4-2022"));
        }
        cursor?.close()

        //eventsData.add(Activity("Title", "Namo" , "Scourge"));
    }

    fun setButtons() {
        backButton = findViewById<View>(R.id.backButton) as Button;
        backButton!!.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(
                this@ToDoListActivity,
                MainActivity::class.java
            )
            //intent.putExtra("Data",eventsData);
            startActivity(intent)
        })

        addEventButton = findViewById<View>(R.id.addMore) as Button;
        addEventButton!!.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(
                this@ToDoListActivity,
                AddEventsActivity::class.java
            )
            //intent.putExtra("Data",eventsData);
            startActivity(intent)
        })
    }

}