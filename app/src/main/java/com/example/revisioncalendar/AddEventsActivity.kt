package com.example.revisioncalendar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class AddEventsActivity : ComponentActivity() {
    var backButton: Button? = null;
    var addEventButton: Button? = null;

    var titleBlock: EditText? = null;
    var typeBlock: EditText? = null;
    var locationBlock: EditText? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_events);
        setButtons();
        setInputs()
    }

    fun setInputs() {
        titleBlock = findViewById<View>(R.id.eventTitleInput) as EditText;
        typeBlock = findViewById<View>(R.id.eventsType) as EditText;
        locationBlock = findViewById<View>(R.id.eventsLocation) as EditText;
    }

    fun setButtons() {
        backButton = findViewById<View>(R.id.backButton) as Button;
        backButton!!.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(
                this@AddEventsActivity,
                ToDoListActivity::class.java
            )
            //intent.putExtra("Data",eventsData);
            startActivity(intent)
        })
        addEventButton = findViewById(R.id.add_button)
        addEventButton!!.setOnClickListener(View.OnClickListener {
            System.out.println(titleBlock?.getText());
            System.out.println(typeBlock?.getText());
            System.out.println(locationBlock?.getText());
            titleBlock?.setText("");
        })
    }
}