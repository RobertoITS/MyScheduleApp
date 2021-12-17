package com.example.myscheduleapp.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.myscheduleapp.mainactivity.MainActivity
import com.example.myscheduleapp.R
import com.example.myscheduleapp.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val anim1: Animation = AnimationUtils.loadAnimation(this, R.anim.translation_down_to_up)
        binding.text1.animation = anim1

        val anim2: Animation = AnimationUtils.loadAnimation(this, R.anim.translation_up_to_down)
        binding.image.animation = anim2

        binding.container.alpha = 0f
        binding.container.animate().alpha(1f).setDuration(1200).setStartDelay(600).start()

        exitSplashScreen()
    }

    private fun exitSplashScreen() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finishAfterTransition()
        }, 2000)
    }
}