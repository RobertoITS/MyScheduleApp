package com.example.myscheduleapp.fragments.newtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myscheduleapp.R
import com.example.myscheduleapp.databinding.FragmentNewTaskBinding
import com.example.myscheduleapp.fragments.newtask.calendar.CalendarFragment

class NewTaskFragment : Fragment() {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)

        val transition = fragmentManager?.beginTransaction()
        transition?.setCustomAnimations(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out
        )
            ?.add(R.id.calendarFrag, CalendarFragment())
            ?.addToBackStack(null)
            ?.commit()

        return binding.root
    }
}