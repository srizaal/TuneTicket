package com.example.ticketingta.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketingta.MainActivity
import com.example.ticketingta.R

import androidx.core.content.ContextCompat;

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //menghilangkan ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash_screen)
        val handler = Handler()
        handler.postDelayed(Runnable {
            startActivity(Intent(applicationContext, Login::class.java))
            finish()
        }, 3000L) //3000 L = 3 detik
    }
}