package com.example.revisioncalendar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revisioncalendar.DataWrapper.Activity
import com.example.revisioncalendar.DataWrapper.EventListAdapter

class ToDoListActivity : ComponentActivity() {
    var eventsData : ArrayList<Activity>? = null
    var backButton : Button? = null;
    var addEventButton : Button? = null;
    var examButton: Button? = null;
    var lectureButton: Button? = null;
    var toDosButton: Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_events);
        setButtons();

        setEventsData()
        updateEventsList()
    }

    fun updateEventsList() {
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
            var content = cursor.getString(cursor.getColumnIndex(DataBaseHandle.NAME_COl));

            var eventContent = Utils.parseString(content)
            eventsData!!.add(Activity(eventContent.title,
                eventContent.type , eventContent.location,
                eventContent.startDate, eventContent.endDate));
        }
        cursor?.close()


        // get the date events
        val extras = intent.extras
        val value = extras!!.getString("Date")

        //eventsData = Utils.filterEndDate(eventsData, value)
    }

    @SuppressLint("Range")
    fun filterEventsData(typeValue: String) {
        setEventsData()

        eventsData = Utils.filterType(eventsData, typeValue)
        updateEventsList()
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
            startActivity(intent)
        })

        examButton = findViewById<View>(R.id.Exam) as Button;
        examButton!!.setOnClickListener(View.OnClickListener {
            filterEventsData("Exam");
        })

        lectureButton = findViewById<View>(R.id.Lecture) as Button;
        lectureButton!!.setOnClickListener(View.OnClickListener {
            filterEventsData("Lecture");
        })

        toDosButton = findViewById<View>(R.id.All) as Button;
        toDosButton!!.setOnClickListener(View.OnClickListener {
            filterEventsData("All");
        })
    }

}