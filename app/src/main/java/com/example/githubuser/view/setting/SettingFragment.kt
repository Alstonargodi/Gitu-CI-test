package com.example.githubuser.view.setting

import android.content.Context
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.data.SettingPreferences
import com.example.githubuser.databinding.FragmentSettingBinding
import com.example.githubuser.viewmodel.util.SettingViewModel
import com.example.githubuser.viewmodel.util.SettingViewModelFactory


private val Context.datastore : DataStore<Preferences> by preferencesDataStore("settings")

class SettingFragment : AppCompatActivity() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var setViewModel : SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefrences = SettingPreferences.getInstance(datastore)
        setViewModel = ViewModelProvider(this, SettingViewModelFactory(prefrences))[SettingViewModel::class.java]

        setViewModel.getThemeSetting().observe(this){ mode : Boolean ->
            if (mode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchmode.isChecked = true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchmode.isChecked = false
            }
        }

        binding.switchmode.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            setViewModel.setThemeSetting(isChecked)
        }


    }




}