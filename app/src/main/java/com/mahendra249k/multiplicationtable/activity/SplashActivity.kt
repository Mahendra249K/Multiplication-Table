package com.mahendra249k.multiplicationtable.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mahendra249k.multiplicationtable.R
import com.mahendra249k.multiplicationtable.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)

        binding.imageViewBackground.setImageResource(R.drawable.ic_splash_background)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }, 3000)

    }
}