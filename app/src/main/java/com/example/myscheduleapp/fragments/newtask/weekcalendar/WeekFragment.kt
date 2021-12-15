package com.example.myscheduleapp.fragments.newtask.weekcalendar

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myscheduleapp.Utils.daysInWeekArray
import com.example.myscheduleapp.Utils.monthYearFromDate
import com.example.myscheduleapp.Utils.selectedDate
import com.example.myscheduleapp.database.AppDataBase
import com.example.myscheduleapp.database.data.NewEventData
import com.example.myscheduleapp.databinding.FragmentWeekBinding
import com.example.myscheduleapp.fragments.newtask.calendar.adapter.CalendarAdapter
import com.example.myscheduleapp.fragments.newtask.weekcalendar.adapter.EventAdapter
import java.time.LocalDate

class WeekFragment : Fragment(), CalendarAdapter.OnItemListener {

    private var _binding: FragmentWeekBinding? = null
    private val binding get() = _binding!!
    private lateinit var newEvent: ArrayList<NewEventData>

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeekBinding.inflate(inflater, container, false)

        //The recycler view of the saved events and his layout manager
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.eventList.layoutManager = llm

        setWeekView()
        setEventAdapter()

        //Button previous week and his functions
        binding.previousWeekBtn.setOnClickListener {
            previousWeekAction()
        }
        //Button next week and his functions
        binding.nextWeekBtn.setOnClickListener {
            nextWeekAction()
        }
        //Button for add new events
        binding.newEventBtn.setOnClickListener {
            newEventAction()
        }

        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setWeekView() {
        binding.month.text = monthYearFromDate(selectedDate!!)
        val days: ArrayList<LocalDate?>? = daysInWeekArray(selectedDate!!)
        val calendarAdapter = CalendarAdapter(days, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 7)
        binding.calendarRecycler.layoutManager = layoutManager
        binding.calendarRecycler.adapter = calendarAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextWeekAction() {
        selectedDate = selectedDate?.plusWeeks(1)
        setWeekView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousWeekAction() {
        selectedDate = selectedDate?.minusWeeks(1)
        setWeekView()
    }

    private fun newEventAction() {
//        startActivity(Intent(this, EventActivity::class.java))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, date: LocalDate) {
        selectedDate = date
        setWeekView()
    }

    override fun onResume() {
        super.onResume()
        setEventAdapter()
    }

    //This adapter bring the info form the data base
    private fun setEventAdapter() {
        //We instantiate the data base reference
        val db: AppDataBase = Room.databaseBuilder(
            requireContext(),
            AppDataBase::class.java,
            "NewEventData")
            .allowMainThreadQueries()
            .build()
        newEvent = arrayListOf()
        //Functions to bring all the data from the data base
        newEvent = db.newEventDao().getAll() as ArrayList<NewEventData>
        binding.eventList.adapter = EventAdapter(newEvent, requireContext())
    }
}