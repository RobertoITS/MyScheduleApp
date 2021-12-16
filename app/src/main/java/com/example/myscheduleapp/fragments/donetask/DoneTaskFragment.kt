package com.example.myscheduleapp.fragments.donetask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myscheduleapp.databinding.FragmentDoneTaskBinding

class DoneTaskFragment : Fragment() {

    private var _binding: FragmentDoneTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneTaskBinding.inflate(inflater, container, false)


        return binding.root
    }
}