package com.example.githubuser


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.data.SettingPreferences
import com.example.githubuser.data.datastore
import com.example.githubuser.databinding.ActivityMainBinding
import com.example.githubuser.viewmodel.util.SettingViewModel
import com.example.githubuser.viewmodel.util.SettingViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var setViewModel : SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefrences = SettingPreferences.getInstance(datastore)
        setViewModel = ViewModelProvider(this, SettingViewModelFactory(prefrences))[SettingViewModel::class.java]

        setViewModel.getThemeSetting().observe(this){ mode : Boolean ->
            if (mode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

    }
}

