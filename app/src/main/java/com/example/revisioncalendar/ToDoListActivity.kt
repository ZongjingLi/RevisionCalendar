package com.example.revisioncalendar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revisioncalendar.DataWrapper.Activity;
import com.example.revisioncalendar.DataWrapper.EventListAdapter
import com.example.revisioncalendar.MainActivity;
class ToDoListActivity : ComponentActivity() {
    var backButton : Button? = null;
    var addEventButton : Button? = null;
    var events = listOf(
        Activity("ISY4699", "Lecture", "TBA"),
        Activity("CS2510", "Lecture", "TBA"),
        Activity("CS2110", "Lecture", "TBA")
    );
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_events);
        setButtons();
        val adapter = EventListAdapter(events)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
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
    }

}