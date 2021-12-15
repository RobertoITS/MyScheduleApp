package com.example.myscheduleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myscheduleapp.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setItemSelected(R.id.mytask)
        openFragment(MyTaskFragment())
        binding.bottomNav.setOnItemSelectedListener {
            when (it){
                R.id.mytask -> {
                    openFragment(MyTaskFragment())
                }
                R.id.newtask -> {
                    openFragment(NewTaskFragment())
                }
                R.id.donetask -> {
                    openFragment(DoneTaskFragment())
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transition = supportFragmentManager.beginTransaction()
        transition
            .setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            .replace(R.id.frag, fragment)
            .commit()
    }
}