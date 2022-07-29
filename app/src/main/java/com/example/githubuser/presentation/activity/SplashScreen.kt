package com.example.githubuser.presentation.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.R
import com.example.core.data.local.preference.SettingPreferences
import com.example.core.data.local.preference.datastore
import com.example.githubuser.presentation.fragment.setting.SettingViewModel
import com.example.githubuser.presentation.utils.viewmodelfactory.SettingViewModelFactory


class SplashScreen : AppCompatActivity() {
    private lateinit var setViewModel : SettingViewModel

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

        val preferences = SettingPreferences.getInstance(datastore)
        setViewModel = ViewModelProvider(this, SettingViewModelFactory(preferences))[SettingViewModel::class.java]

        setViewModel.getThemeSetting().observe(this){ mode : Boolean ->
            if (mode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }



        window.statusBarColor = ContextCompat.getColor(this, R.color.maincolor)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.maincolor)
    }
}