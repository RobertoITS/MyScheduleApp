package com.example.myscheduleapp.fragments.newtask.weekcalendar.newevent

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.myscheduleapp.R
import com.example.myscheduleapp.Utils.formattedDate
import com.example.myscheduleapp.Utils.formattedTime
import com.example.myscheduleapp.Utils.selectedDate
import com.example.myscheduleapp.database.AppDataBase
import com.example.myscheduleapp.database.data.NewEventData
import com.example.myscheduleapp.databinding.FragmentEventBinding
import java.time.LocalTime

class EventFragment : Fragment() {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    private var id: Int? = null

    private lateinit var time: LocalTime

    var eventsList: ArrayList<NewEventData> = ArrayList()

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(inflater, container, false)

        time = LocalTime.now()

        binding.eventDate.text = ("Date: " + formattedDate(selectedDate!!))

        binding.eventTime.text = ("Time: " + formattedTime(time))

        binding.saveAction.setOnClickListener {
            saveEventAction()
        }

        return binding.root
    }

    //Esta lista se guarda en la bd
    private fun saveEventAction() {
        val db: AppDataBase = Room.databaseBuilder(
            requireContext(),
            AppDataBase::class.java,
            "NewEventData")
            .allowMainThreadQueries()
            .build()
        val eventNameString: String = binding.eventName.text.toString()
        val newEvent = NewEventData(id, eventNameString, selectedDate.toString(), time.toString())
        eventsList.add(newEvent)
        db.newEventDao().insert(eventsList)
        val frag = fragmentManager?.findFragmentById(R.id.newEventFrag)!!
        val transition = fragmentManager?.beginTransaction()!!
        transition
                .setCustomAnimations(
                    R.anim.frag_down_to_up,
                    R.anim.frag_up_to_down
                )
                .remove(frag)
                .commit()
    }
}