package com.example.myscheduleapp.fragments.newtask.weekcalendar.newevent

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import com.example.myscheduleapp.R
import com.example.myscheduleapp.Utils.formattedDate
import com.example.myscheduleapp.Utils.formattedTime
import com.example.myscheduleapp.Utils.selectedDate
import com.example.myscheduleapp.database.AppDataBase
import com.example.myscheduleapp.database.data.NewEventData
import java.time.LocalTime

class EventFragment : Fragment() {

    private var eventName: EditText? = null
    private var eventDate: TextView? = null
    private var eventTime: TextView? = null
    private var saveAction: Button? = null
    private var id: Int? = null

    private lateinit var time: LocalTime

    var eventsList: ArrayList<NewEventData> = ArrayList()

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_event, container, false)

        eventName = view.findViewById(R.id.eventName)
        eventDate = view.findViewById(R.id.eventDate)
        eventTime = view.findViewById(R.id.eventTime)
        saveAction = view.findViewById(R.id.saveAction)

        time = LocalTime.now()

        eventDate?.text = ("Date: " + formattedDate(selectedDate!!))

        eventTime?.text = ("Time: " + formattedTime(time))

        saveAction?.setOnClickListener {
            saveEventAction()
        }

        return view
    }


    //Esta lista se guarda en la bd
    fun saveEventAction() {
//        val app = applicationContext as NewEventApp
        val db: AppDataBase = Room.databaseBuilder(
            requireContext(),
            AppDataBase::class.java,
            "NewEventData")
            .allowMainThreadQueries()
            .build()
        val eventNameString: String = eventName?.text.toString()
        val newEvent = NewEventData(id, eventNameString, selectedDate.toString(), time.toString())
        eventsList.add(newEvent)
        db.newEventDao().insert(eventsList)
        //Cambiar al otro fragment
    }

}