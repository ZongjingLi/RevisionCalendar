package com.example.revisioncalendar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.revisioncalendar.Utils

class AddEventsActivity : ComponentActivity() {
    var backButton: Button? = null;
    var addEventButton: Button? = null;

    var titleBlock: EditText? = null;
    var typeBlock: EditText? = null;
    var locationBlock: EditText? = null;
    var startDateBlock: EditText? = null;
    var endDateBlock: EditText? = null;
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
        startDateBlock = findViewById<View>(R.id.StartDate) as EditText;
        endDateBlock = findViewById<View>(R.id.endDate) as EditText;
    }

    var popupWindow: PopupWindow? = null
    var showText: TextView? = null
    fun showPopUpWindow(content: String, v: View){
        val inflater = LayoutInflater.from(this)
        val myview = inflater.inflate(R.layout.popup_window, null) //引用自定义布局
        popupWindow = PopupWindow(myview, 1600, 1100) //后面是像素大小

        showText = findViewById(R.id.name)
        showText?.setText(content);


        myview.findViewById<View>(R.id.close).setOnClickListener {
            //updateActivities();
            popupWindow!!.dismiss() //点击按钮对话框消
            //Toast.makeText(this, "Clicked Leave", Toast.LENGTH_SHORT).show()
        }
        //System.out.println(intent.extras!!.get("Username"));
        popupWindow!!.showAtLocation(v, 1, 0, 100)
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


            var title = titleBlock?.getText().toString()
            var type = typeBlock?.getText().toString()
            var location = locationBlock?.getText().toString()
            var start_time = startDateBlock?.getText().toString()//"23-3-2024"
            var end_time = endDateBlock?.getText()//"23-3-2024"

            if (Utils.validName(title) && Utils.validTime(start_time)) {
                val db = DataBaseHandle(this, null)
                val content = String.format("%s|%s|%s|%s|%s",title, type, location, start_time, end_time)
                System.out.println(content)
                db.addCourse(content)

                titleBlock?.setText("");
                typeBlock?.setText("");
                locationBlock?.setText("");
                startDateBlock?.setText("");
                endDateBlock?.setText("");
                //showPopUpWindow("Added Successfully", View)

            } else {
                titleBlock?.setText("Invalid Inputs!");
                typeBlock?.setText("Invalid Inputs!");
                locationBlock?.setText("Invalid Inputs!");
                startDateBlock?.setText("Invalid Inputs!");
                endDateBlock?.setText("Invalid Inputs!");
                //showPopUpWindow("Something is wrong", View);
            }
        })
    }
}