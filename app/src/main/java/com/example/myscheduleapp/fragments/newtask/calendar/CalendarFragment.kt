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
import com.example.myscheduleapp.databinding.FragmentCalendarBinding
import com.example.myscheduleapp.fragments.newtask.calendar.adapter.CalendarAdapter
import com.example.myscheduleapp.fragments.newtask.weekcalendar.WeekFragment
import java.time.LocalDate

class CalendarFragment : Fragment(), CalendarAdapter.OnItemListener {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)

        selectedDate = LocalDate.now()

        setMonthView()

        binding.previousMonth.setOnClickListener {
            previousMonthAction()
        }

        binding.nextMonth.setOnClickListener {
            nextMonthAction()
        }

        binding.weekAction.setOnClickListener {
            weeklyAction()
        }

        return binding.root

        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun setMonthView() {
            binding.month.text = monthYearFromDate(selectedDate!!)
            val daysInMonth = daysInMonthArray(selectedDate!!)
            val calendarAdapter = CalendarAdapter(daysInMonth, this)
            val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 7)
            binding.calendarRecycler.layoutManager = layoutManager
            binding.calendarRecycler.adapter = calendarAdapter
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