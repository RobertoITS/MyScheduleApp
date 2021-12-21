package com.example.myscheduleapp.fragments.mytask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myscheduleapp.SwipeGesture
import com.example.myscheduleapp.database.AppDataBase
import com.example.myscheduleapp.database.data.NewEventData
import com.example.myscheduleapp.databinding.FragmentMyTaskBinding
import com.example.myscheduleapp.fragments.mytask.adapter.EventAdapter
class MyTaskFragment : Fragment() {

    private var _binding: FragmentMyTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var newEvent: ArrayList<NewEventData>
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyTaskBinding.inflate(inflater, container, false)

        //The recycler view of the saved events and his layout manager
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.eventList.layoutManager = llm
        setEventAdapter()

        return binding.root
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
        newEvent = db.newEventDao().getAllUnDoneEvents() as ArrayList<NewEventData>
        eventAdapter = EventAdapter(newEvent, requireContext())
        binding.eventList.adapter = eventAdapter

        val swipeGesture = object : SwipeGesture (requireContext()) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val item: NewEventData = newEvent[viewHolder.absoluteAdapterPosition]
                val index: Int = viewHolder.absoluteAdapterPosition
                when (direction){
                    ItemTouchHelper.LEFT -> {
                        eventAdapter.removeItems(viewHolder.absoluteAdapterPosition)
                        }

                    ItemTouchHelper.RIGHT -> {
                        eventAdapter.restoreItem(item, index)
                    }
                }

            }

        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.eventList)
    }
}