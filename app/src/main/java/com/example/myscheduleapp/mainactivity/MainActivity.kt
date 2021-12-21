package com.example.myscheduleapp.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.myscheduleapp.R
import com.example.myscheduleapp.databinding.ActivityMainBinding
import com.example.myscheduleapp.fragments.donetask.DoneTaskFragment
import com.example.myscheduleapp.fragments.mytask.MyTaskFragment
import com.example.myscheduleapp.fragments.newtask.NewTaskFragment
import com.example.myscheduleapp.fragments.newtask.weekcalendar.WeekFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var enter: Animation? = null
    var exit: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enter = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        exit = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        openFragment(MyTaskFragment(), R.id.frag)
        openFragment(NewTaskFragment(), R.id.frag2)
        openFragment(DoneTaskFragment(), R.id.frag3)


        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        binding.bottomNav.setItemSelected(R.id.mytask)

        binding.bottomNav.setOnItemSelectedListener {
            when (it){
                R.id.mytask -> {
                    change(1)
                }
                R.id.newtask -> {
                    change(2)
                }
                R.id.donetask -> {
                    change(3)
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment, frag: Int) {
        val transition = supportFragmentManager.beginTransaction()
        transition
//            .setCustomAnimations(
//                R.anim.fade_in,
//                R.anim.fade_out,
//                R.anim.fade_in,
//                R.anim.fade_out
//            )
            .add(frag, fragment)
            .commit()
    }

    fun change(int: Int){
        when (int){
            1 -> {
                if (binding.frag2.alpha != 0f){
                    binding.frag2.visibility = View.GONE
                }
                if (binding.frag3.alpha != 0f) {
                    binding.frag3.visibility = View.GONE
                }
                binding.frag.visibility = View.VISIBLE
            }
            2 -> {
                if (binding.frag.alpha != 0f){
                    binding.frag.visibility = View.GONE
                }
                if (binding.frag3.alpha != 0f){
                    binding.frag3.visibility = View.GONE
                }
                binding.frag2.visibility = View.VISIBLE
            }
            3 -> {
                if (binding.frag2.alpha != 0f){
                    binding.frag2.visibility = View.GONE
                }
                if (binding.frag.alpha != 0f) {
                    binding.frag.visibility = View.GONE
                }
                binding.frag3.visibility = View.VISIBLE
            }
        }
    }
}