package com.example.githubuser.view


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.example.githubuser.MainActivity
import com.example.githubuser.R


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    companion object{
        const val TIME = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, TIME)


        window.statusBarColor = ContextCompat.getColor(this, R.color.maincolor)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.maincolor)
    }
}