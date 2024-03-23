package com.example.revisioncalendar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CalendarView
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.revisioncalendar.DataWrapper.Activity
import com.example.revisioncalendar.ui.theme.RevisionCalendarTheme

class MainActivity : ComponentActivity() {
    var buttonToActivity: Button? = null;
    var eventsData: ArrayList<Activity> = ArrayList<Activity>();
    var calendarView: CalendarView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main);
        setupWigits();
        setupCalendar();
        setupData();
        System.out.println("This is an actual real page.");
        System.out.println(eventsData.get(0));
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
            System.out.println("This is button onclick");
        })
        System.out.println("Try to setup the wigets.");
    }

    fun setupCalendar() {
        calendarView = findViewById(R.id.main_calendar) as CalendarView;
        System.out.println("Find the main calendar view.");
    }

    fun setupData() {
        eventsData.add(Activity("Scourge"));
    }

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RevisionCalendarTheme {
        Greeting("Android")
    }
}