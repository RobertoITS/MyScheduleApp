package com.example.myscheduleapp.fragments.newtask.weekcalendar.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myscheduleapp.R
import com.example.myscheduleapp.database.AppDataBase
import com.example.myscheduleapp.database.data.NewEventData

//adaptador para la lista de eventos nuevos
//Agregamos la variable para tomar el contexto desde la app, y asi modificar la BD
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
        holder.visibilityOnOff.setOnClickListener {
            currentEvent.visibility = !currentEvent.visibility
            notifyItemChanged(position)
        }

        holder.delete.setOnClickListener {
            val db: AppDataBase = Room.databaseBuilder(app,
                AppDataBase::class.java,
                "NewEventData")
                .allowMainThreadQueries()
                .build()
            db.newEventDao().delete(currentEvent)
            notifyItemRemoved(position)
            notifyDataSetChanged()
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
        val visibilityOnOff: LinearLayout = itemView.findViewById(R.id.visibilityOn_Off)
        val delete: Button = itemView.findViewById(R.id.delete)
    }
}