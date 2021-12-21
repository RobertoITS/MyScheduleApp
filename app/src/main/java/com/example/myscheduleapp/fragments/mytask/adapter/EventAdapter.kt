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
        holder.viewF.setOnClickListener {
            currentEvent.visibility = !currentEvent.visibility
            notifyItemChanged(position)
        }

        holder.viewB.setOnClickListener {
            val db: AppDataBase = Room.databaseBuilder(app,
                AppDataBase::class.java,
                "NewEventData")
                .allowMainThreadQueries()
                .build()
            db.newEventDao().delete(currentEvent)
            newEventList.removeAt(position)
            notifyItemRemoved(position)
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
        val viewB: RelativeLayout = itemView.findViewById(R.id.delete_background)
    }

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

    fun restoreItem(item: NewEventData, position: Int){
        newEventList.add(position, item)
//        val db: AppDataBase = Room.databaseBuilder(
//            app,
//            AppDataBase::class.java,
//            "NewEventData")
//            .allowMainThreadQueries()
//            .build()
//        db.newEventDao().insert(newEventList)
//        notifyItemInserted(position)
    }
}