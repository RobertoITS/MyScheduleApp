package com.example.myscheduleapp.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myscheduleapp.R
import com.example.myscheduleapp.databinding.ActivityMainBinding
import com.example.myscheduleapp.fragments.donetask.DoneTaskFragment
import com.example.myscheduleapp.fragments.mytask.MyTaskFragment
import com.example.myscheduleapp.fragments.newtask.NewTaskFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val home = MyTaskFragment()
    private val new = NewTaskFragment()
    private val done = DoneTaskFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFrag(home)

        binding.bottomNav.setItemSelected(R.id.mytask)

        binding.bottomNav.setOnItemSelectedListener {
            when (it) {
                R.id.mytask -> {
                    openFragment(home)
                }
                R.id.newtask -> {
                    openFragment(new)
                }
                R.id.donetask -> {
                    openFragment(done)
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        val currentFrag = supportFragmentManager.findFragmentById(R.id.frag)
        val transition = supportFragmentManager.beginTransaction()
        if (fragment.isAdded){
            transition.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
                .hide(currentFrag!!)
                .show(fragment)
                .commit()
        } else {
            transition
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                .hide(currentFrag!!)
                .add(R.id.frag, fragment)
                .commit()
        }
    }

    private fun addFrag(fragment: Fragment) {
        val currentFrag = supportFragmentManager.findFragmentById(R.id.frag)
        val transition = supportFragmentManager.beginTransaction()
        if (currentFrag == null) {
            transition
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                .add(R.id.frag, fragment)
                .commit()
        } else {
            transition
                .setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                .hide(currentFrag)
                .add(R.id.frag, fragment)
                .commit()
        }
    }
}