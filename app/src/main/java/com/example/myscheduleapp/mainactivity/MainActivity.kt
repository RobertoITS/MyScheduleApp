package com.example.myscheduleapp.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myscheduleapp.R
import com.example.myscheduleapp.databinding.ActivityMainBinding
import com.example.myscheduleapp.fragments.donetask.DoneTaskFragment
import com.example.myscheduleapp.fragments.mytask.MyTaskFragment
import com.example.myscheduleapp.fragments.newtask.NewTaskFragment
import com.example.myscheduleapp.fragments.newtask.calendar.CalendarFragment
import com.example.myscheduleapp.fragments.newtask.weekcalendar.WeekFragment
import com.example.myscheduleapp.fragments.newtask.weekcalendar.newevent.EventFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val home = MyTaskFragment()
    private val new = NewTaskFragment()
    private val done = DoneTaskFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //The idea is next: using hide/show, we can accelerate the transition between fragments

        addFragment(new)
        hideFragment(new)

        addFragment(done)
        hideFragment(done)

        addFragment(home)

        binding.bottomNav.setItemSelected(R.id.mytask)

        binding.bottomNav.setOnItemSelectedListener {
            when (it) {
                R.id.mytask -> {
                    if (new.isVisible) hideFragment(new)
                    else hideFragment(done)
                    showFragment(home)
                }
                R.id.newtask -> {
                    if (done.isVisible) hideFragment(done)
                    else hideFragment(home)
                    showFragment(new)
                }
                R.id.donetask -> {
                    if (home.isVisible) hideFragment(home)
                    else hideFragment(new)
                    showFragment(done)
                }
            }
        }
    }

    //First we add all the fragments
    private fun addFragment (f1: Fragment){
        val t = supportFragmentManager.beginTransaction()
        t.setCustomAnimations(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out
        )
        t.add(R.id.frag, f1)
        t.commit()
    }

    //Then, we hide them
    private fun hideFragment (f1: Fragment){
        val t = supportFragmentManager.beginTransaction()
        t.setCustomAnimations(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out
        )
        t.hide(f1)
        t.commit()
    }

    //At last we vary between them
    private fun showFragment (f1: Fragment){
        val t = supportFragmentManager.beginTransaction()
        t.setCustomAnimations(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out
        )
        t.show(f1)
        t.commit()
    }
}