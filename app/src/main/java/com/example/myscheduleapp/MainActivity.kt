package com.example.myscheduleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.example.myscheduleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.image.alpha = 0f
        binding.text1.translationX = -800f
        binding.text2.translationX = 800f
        binding.text1
            .animate()
            .translationX(0f)
            .setDuration(1200)
            .setStartDelay(600)
            .start()
        binding.text2
            .animate()
            .translationX(0f)
            .setDuration(1200)
            .setStartDelay(600)
            .start()

        binding.image
            .animate()
            .alpha(1f)
            .setDuration(1200)
            .setStartDelay(600)
            .start()
        debounce()
    }
    private fun debounce() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this, MainActivity2::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 3000)
    }
}