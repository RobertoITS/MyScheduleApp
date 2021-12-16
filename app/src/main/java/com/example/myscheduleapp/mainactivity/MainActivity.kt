package com.example.myscheduleapp.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myscheduleapp.Communicator
import com.example.myscheduleapp.R
import com.example.myscheduleapp.databinding.ActivityMainBinding
import com.example.myscheduleapp.fragments.donetask.DoneTaskFragment
import com.example.myscheduleapp.fragments.mytask.MyTaskFragment
import com.example.myscheduleapp.fragments.newtask.NewTaskFragment

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityMainBinding
    var communicator: Communicator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
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

    override fun stateChange(boolean: Boolean) {
        if (boolean) communicator?.stateChange(true)
    }

    override fun itemRemoved(item: Int) {

    }
}