package com.example.myscheduleapp.fragments.newtask.calendar.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myscheduleapp.R
import com.example.myscheduleapp.Utils.selectedDate
import java.time.LocalDate


class CalendarAdapter(
    private val days: ArrayList<LocalDate?>?,
    private val onItemListener: OnItemListener
) :
    RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams = view.layoutParams
        if (days?.size!! > 15){
            layoutParams.height = (parent.height * 0.166666666).toInt()
        }
        else{
            layoutParams.height = parent.height
        }
        return CalendarViewHolder(view, onItemListener, days)
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date: LocalDate? = days?.get(position)
        if (date == null){
            holder.dayOfMonth.text = ""
        }
        else{
            holder.dayOfMonth.text = date.dayOfMonth.toString()
            if (date == selectedDate){
                holder.parentView.setBackgroundColor(R.color.newTask)
            }
        }

    }

    override fun getItemCount(): Int {
        return days?.size!!
    }

    class CalendarViewHolder(itemView: View, private val onItemListener: OnItemListener, private val days: ArrayList<LocalDate?>?) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val dayOfMonth: TextView = itemView.findViewById(R.id.cellDayText)
        val parentView: View = itemView.findViewById(R.id.parent_view)
        override fun onClick(view: View) {
            onItemListener.onItemClick(adapterPosition, days?.get(adapterPosition)!!)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface OnItemListener {
        fun onItemClick(position: Int, date: LocalDate)
    }
}