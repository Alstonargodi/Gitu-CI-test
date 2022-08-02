package com.example.githubuser.presentation.activity


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.githubuser.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
//            val bookFragment =
//                Class.forName("com.example.favorite.activity.FavoritePeopleActivity").newInstance() as Activity
//            startActivity(Intent(this,bookFragment::class.java))
        }, TIME)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        window.statusBarColor = ContextCompat.getColor(this, R.color.maincolor)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.maincolor)


    }
}