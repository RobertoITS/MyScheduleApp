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
import androidx.recyclerview.widget.RecyclerView
import com.example.myscheduleapp.Communicator
import com.example.myscheduleapp.R
import com.example.myscheduleapp.Utils.daysInWeekArray
import com.example.myscheduleapp.Utils.monthYearFromDate
import com.example.myscheduleapp.Utils.selectedDate
import com.example.myscheduleapp.databinding.FragmentWeekBinding
import com.example.myscheduleapp.fragments.newtask.calendar.adapter.CalendarAdapter
import com.example.myscheduleapp.fragments.newtask.weekcalendar.newevent.EventFragment
import java.time.LocalDate

class WeekFragment : Fragment(), CalendarAdapter.OnItemListener, Communicator {

    private var _binding: FragmentWeekBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWeekBinding.inflate(inflater, container, false)

        setWeekView()

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
        val transition = fragmentManager?.beginTransaction()
        val frag = fragmentManager?.findFragmentById(R.id.newEventFrag)
        transition
            ?.setCustomAnimations(
                R.anim.frag_down_to_up,
                R.anim.frag_up_to_down
            )
        when (binding.newEventBtn.text) {
            getString(R.string.new_task) -> {
                transition
                    ?.add(R.id.newEventFrag, EventFragment())
                    ?.commit()
                binding.newEventBtn.text = getString(R.string.back_event)
            }
            getString(R.string.back_event) ->{
                if (frag != null) {
                    transition
                        ?.remove(frag)
                        ?.commit()
                }
                binding.newEventBtn.text = getString(R.string.new_task)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, date: LocalDate) {
        selectedDate = date
        setWeekView()
    }

    override fun stateChange(boolean: Boolean) {
        if (boolean) binding.newEventBtn.text = getString(R.string.new_task)
    }

    override fun itemRemoved(item: Int) {
    }
}