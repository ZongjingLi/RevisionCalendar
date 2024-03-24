package com.example.revisioncalendar.DataWrapper
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.revisioncalendar.DataBaseHandle
import com.example.revisioncalendar.R
import com.google.android.material.snackbar.Snackbar

class EventListAdapter(private var events: ArrayList<Activity>?): RecyclerView.Adapter<EventListAdapter.ViewHolder>(){
    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.objTitle)
        val typeTextView:  TextView = itemView.findViewById(R.id.eventType)
        val timeTextView:  TextView = itemView.findViewById(R.id.eventTime)
        val locationTextView: TextView = itemView.findViewById(R.id.eventLocation)
        val button: Button = itemView.findViewById(R.id.closeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.event_item,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = events!!.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events?.get(position)
        holder.apply{
            titleTextView.text = event?.title
            typeTextView.text = event?.type
            timeTextView.text = event?.endDate //"Date"//"${events.from.toLocalDate()} ${events.from.toLocalTime()} - ${events.to.toLocalTime()}"
            locationTextView.text = event?.location
            var i = position
            button.setOnClickListener(
                fun(v: View?) {
                    // Perform action on click
                    //DataBaseHandle db = new DataBaseHandle(this, null);
                    val content =
                        String.format(
                            "%s|%s|%s|%s|%s",
                            events?.get(i)!!.title,
                            events?.get(i)!!.type,
                            events!!.get(i).location,
                            events!!.get(i).startDate,
                            events!!.get(i).endDate
                        )

                    val db = v?.let { it1 -> DataBaseHandle(it1.context, null) }
                    if (db != null) {
                        db.deleteCourse(content)
                    }

                    if (v != null) {
                        Snackbar.make(
                            v,
                            "Deleted",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                    println("Deleted")
                }
            )
        }
        System.out.println(position);

    }
}