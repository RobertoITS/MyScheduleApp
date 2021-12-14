package com.example.myscheduleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.myscheduleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val TIME_SPLASH_SCREEN = 1200L
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(binding.root)

        val anim1: Animation = AnimationUtils.loadAnimation(this, R.anim.translation_right)
        binding.text1.animation = anim1

        binding.container.alpha = 0f
        binding.container.animate().alpha(1f).setDuration(1200).setStartDelay(600).start()
//
//        binding.text1.translationX = -800f
//        binding.text1.animate().translationX(0f).setDuration(1200).setStartDelay(600).start()

        binding.text2.translationX = 800f
        binding.text2.animate().translationX(0f).setDuration(1200).setStartDelay(600).start()

        binding.image.alpha = 0f
        binding.image.animate().alpha(1f).setDuration(1200).setStartDelay(600).start()

        exitSplashScreen()
    }

    private fun exitSplashScreen() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }, 4000)
    }

}