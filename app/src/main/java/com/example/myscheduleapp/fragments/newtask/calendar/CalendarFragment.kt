package com.example.myscheduleapp.fragments.newtask.calendar

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myscheduleapp.R
import com.example.myscheduleapp.Utils.daysInMonthArray
import com.example.myscheduleapp.Utils.monthYearFromDate
import com.example.myscheduleapp.Utils.selectedDate
import com.example.myscheduleapp.fragments.newtask.calendar.adapter.CalendarAdapter
import com.example.myscheduleapp.fragments.newtask.weekcalendar.WeekFragment
import java.time.LocalDate

class CalendarFragment : Fragment(), CalendarAdapter.OnItemListener {

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecycler: RecyclerView
    private lateinit var previousMonth: Button
    private lateinit var nextMonth: Button
    private lateinit var weekAction: Button

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        monthYearText = view.findViewById(R.id.month)

        calendarRecycler = view.findViewById(R.id.calendarRecycler)

        previousMonth = view.findViewById(R.id.previousMonth)

        nextMonth = view.findViewById(R.id.nextMonth)

        weekAction = view.findViewById(R.id.weekAction)

        selectedDate = LocalDate.now()

        setMonthView()

        previousMonth.setOnClickListener {
            previousMonthAction()
        }

        nextMonth.setOnClickListener {
            nextMonthAction()
        }

        weekAction.setOnClickListener {
            weeklyAction()
        }

        return view

        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun setMonthView() {
            monthYearText.text = monthYearFromDate(selectedDate!!)
            val daysInMonth = daysInMonthArray(selectedDate!!)
            val calendarAdapter = CalendarAdapter(daysInMonth, this)
            val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 7)
            calendarRecycler.layoutManager = layoutManager
            calendarRecycler.adapter = calendarAdapter
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun nextMonthAction() {
            selectedDate = selectedDate?.plusMonths(1)
            setMonthView()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun previousMonthAction() {
            selectedDate = selectedDate?.minusMonths(1)
            setMonthView()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onItemClick(position: Int, date: LocalDate) {
            if (date != null){
                selectedDate = date
                setMonthView()
            }

        }

        private fun weeklyAction() {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
                ?.replace(R.id.frag, WeekFragment())
                ?.commit()
        }

}