package com.example.revisioncalendar.DataWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.revisioncalendar.R

class EventListAdapter(private var events: List<Activity>): RecyclerView.Adapter<EventListAdapter.ViewHolder>(){
    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.objTitle)
        val typeTextView:  TextView = itemView.findViewById(R.id.eventType)
        val timeTextView:  TextView = itemView.findViewById(R.id.eventTime)
        val locationTextView: TextView = itemView.findViewById(R.id.eventLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.event_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = events.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val events = events[position]
        holder.apply{
            titleTextView.text = events.title
            typeTextView.text = events.type
            timeTextView.text = "Date"//"${events.from.toLocalDate()} ${events.from.toLocalTime()} - ${events.to.toLocalTime()}"
            locationTextView.text = events.location
        }

    }
}