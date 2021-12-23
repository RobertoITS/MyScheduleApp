package com.example.myscheduleapp.fragments.mytask.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myscheduleapp.R
import com.example.myscheduleapp.database.AppDataBase
import com.example.myscheduleapp.database.data.NewEventData
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**Adapter for the list of new events.
 Add the variable to take the context
 from the app, and thus modify the DB**/
class EventAdapter(
    private val newEventList: ArrayList<NewEventData>,
    var app: Context
    ): RecyclerView.Adapter<EventAdapter.EventViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_cell, parent, false)

        return EventViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        val currentEvent = newEventList[position]
        holder.eventCell.text = currentEvent.name
        holder.dateCell.text = currentEvent.date
        holder.hourCell.text = currentEvent.time

        //VISIBILITY
        val isVisible: Boolean = currentEvent.visibility
        holder.hiddenContainer.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.viewF.setOnClickListener {
            currentEvent.visibility = !currentEvent.visibility
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return newEventList.size
    }

    class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val eventCell: TextView = itemView.findViewById(R.id.eventCell)
        val dateCell: TextView = itemView.findViewById(R.id.dateCell)
        val hourCell: TextView = itemView.findViewById(R.id.hourCell)
        val hiddenContainer: LinearLayout = itemView.findViewById(R.id.hiddenContainer)
        val viewF: LinearLayout = itemView.findViewById(R.id.event_card)
    }

    //Delete the entry
    fun removeItems(position: Int){
        val currentEvent = newEventList[position]
        val db: AppDataBase = Room.databaseBuilder(app,
            AppDataBase::class.java,
            "NewEventData")
            .allowMainThreadQueries()
            .build()
        db.newEventDao().delete(currentEvent)
        newEventList.removeAt(position)
        notifyItemRemoved(position)
    }

    //Restore the entry deleted
    fun restoreItem(item: NewEventData, position: Int){
        newEventList.add(position, item)
        val db: AppDataBase = Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            "NewEventData")
            .allowMainThreadQueries()
            .build()
        db.newEventDao().insert(newEventList)
        notifyItemInserted(position)
    }
}