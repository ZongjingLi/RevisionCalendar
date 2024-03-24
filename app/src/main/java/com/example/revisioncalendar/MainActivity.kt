package com.example.revisioncalendar

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.ListView
import androidx.activity.ComponentActivity
import com.example.revisioncalendar.DataWrapper.Activity
import com.example.revisioncalendar.Utils

class MainActivity : ComponentActivity() {
    var buttonToActivity: Button? = null;
    var eventsData: ArrayList<Activity> = ArrayList<Activity>();
    var calendarView: CalendarView? = null;
    var toDoList: ListView? = null

    var curDate: String? = "23-3-2024";

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main);
        setupWigits();
        setupCalendar();
        setupData();
        updateItems();

        val db = DataBaseHandle(this, null)


        //db.addCourse("ISYE4699", "Lecture", "TBA", "25-3-2024", "25-4-2024");


        val cursor = db.getCourse()
        cursor!!.moveToFirst()
        //System.out.println(cursor.getColumnIndex(DataBaseHandle.STARTDATE_COL));
        //System.out.println(cursor.getColumnIndex(DataBaseHandle.ENDDATE_COL));

        while(cursor.moveToNext()){
            //System.out.println(cursor.getString(cursor.getColumnIndex(DataBaseHandle.NAME_COl)) + "\n")
            db.deleteCourse(cursor.getString(cursor.getColumnIndex(DataBaseHandle.NAME_COl)) );
        }
        System.out.println(Utils.);
        db.addCourse("CS2110")
        db.addCourse("ISYE4699")
        db.addCourse("CS2550")
        cursor?.close()
    }

    fun setupWigits() {
        buttonToActivity = findViewById<View>(R.id.toSpecificButton) as Button
        buttonToActivity!!.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(
                this@MainActivity,
                ToDoListActivity::class.java
            )
            //intent.putExtra("Data",eventsData);
            startActivity(intent)
        })
    }

    fun setupCalendar() {
        calendarView = findViewById(R.id.main_calendar) as CalendarView;
        calendarView!!.setOnDateChangeListener(
            OnDateChangeListener { view, year, month, dayOfMonth ->
                val curDate = (dayOfMonth.toString() + "-"
                        + (month + 1) + "-" + year)
                updateItems()
            }
        )
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
            eventsData.add(Activity(title, "Lecture" , "Scourge", "25-3-2022", "25-4-2022"));
        }
        cursor?.close()

        //eventsData.add(Activity("Title", "Namo" , "Scourge"));
    }

    fun updateItems() {
        // Deal with the doto list section.
        toDoList = findViewById(R.id.mobile_list)
        setEventsData()
        val customAdapter = ItemAdapter(applicationContext, eventsData)
        toDoList!!.setAdapter(customAdapter)
    }

    fun setupData() {
        val db = DataBaseHandle(this, null)
    }

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }


}
