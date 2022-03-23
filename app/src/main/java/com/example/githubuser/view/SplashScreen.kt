package com.example.githubuser.view



import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.MainActivity
import com.example.githubuser.R
import com.example.githubuser.data.SettingPreferences
import com.example.githubuser.data.datastore
import com.example.githubuser.viewmodel.util.SettingViewModel
import com.example.githubuser.viewmodel.util.SettingViewModelFactory


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

        val prefrences = SettingPreferences.getInstance(datastore)
        setViewModel = ViewModelProvider(this, SettingViewModelFactory(prefrences))[SettingViewModel::class.java]

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