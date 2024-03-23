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

class MainActivity : ComponentActivity() {
    var buttonToActivity: Button? = null;
    var eventsData: ArrayList<Activity> = ArrayList<Activity>();
    var calendarView: CalendarView? = null;
    var toDoList: ListView? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main);
        setupWigits();
        setupCalendar();
        setupData();
        updateItems();

        val db = DataBaseHandle(this, null)
        //db.addCourse("Namomo", "Scourge");
        //db.addCourse("Acherus", "NewAvalon")

        val cursor = db.getCourse()
        cursor!!.moveToFirst()

        while(cursor.moveToNext()){
            System.out.println(cursor.getString(cursor.getColumnIndex(DataBaseHandle.NAME_COl)) + "\n")
            System.out.println(cursor.getString(cursor.getColumnIndex(DataBaseHandle.AGE_COL)) + "\n")
            db.deleteCourse(cursor.getString(cursor.getColumnIndex(DataBaseHandle.NAME_COl)) );
        }
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
                val Date = (dayOfMonth.toString() + "-"
                        + (month + 1) + "-" + year)
                updateItems()
            }
        )
    }

    fun updateItems() {
        // Deal with the doto list section.
        toDoList = findViewById(R.id.mobile_list)
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
