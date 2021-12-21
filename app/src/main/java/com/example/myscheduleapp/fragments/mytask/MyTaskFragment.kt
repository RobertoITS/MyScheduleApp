package com.example.myscheduleapp.fragments.mytask

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myscheduleapp.CallBackItemTouch
import com.example.myscheduleapp.MyItemTouchHelperCallback
import com.example.myscheduleapp.database.AppDataBase
import com.example.myscheduleapp.database.data.NewEventData
import com.example.myscheduleapp.databinding.FragmentMyTaskBinding
import com.example.myscheduleapp.fragments.mytask.adapter.EventAdapter
import com.google.android.material.snackbar.Snackbar

class MyTaskFragment : Fragment(), CallBackItemTouch {

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
        val callback: ItemTouchHelper.Callback = MyItemTouchHelperCallback(this)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.eventList)
    }

    override fun itemTouchOnMode(oldPosition: Int, newPosition: Int) {
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val name = newEvent[viewHolder.adapterPosition].name
        val event: NewEventData = newEvent[viewHolder.adapterPosition]
        val index: Int = viewHolder.adapterPosition
        eventAdapter.removeItems(viewHolder.adapterPosition)
        eventAdapter.notifyItemRemoved(viewHolder.adapterPosition)
        val snack = Snackbar.make(binding.snack, "$name => ELIMINADO", Snackbar.LENGTH_LONG)
        snack.setAction("Cancelar/Deshacer") {
            eventAdapter.restoreItem(event, index)
        }
        snack.setActionTextColor(Color.GREEN)
        snack.show()
    }
}