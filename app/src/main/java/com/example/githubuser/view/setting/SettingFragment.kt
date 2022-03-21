package com.example.githubuser.view.setting

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.R
import com.example.githubuser.data.SettingPreferences
import com.example.githubuser.databinding.FragmentSettingBinding
import com.example.githubuser.viewmodel.util.SettingViewModel
import com.example.githubuser.viewmodel.util.SettingViewModelFactory
import com.example.githubuser.viewmodel.util.ViewModelFactory
import com.example.githubuser.viewmodel.util.obtainViewModel


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
                Log.d("mode","night mode")
                binding.switchmode.isChecked = true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Log.d("mode","light mode")
                binding.switchmode.isChecked = false
            }
        }

        binding.switchmode.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            setViewModel.setThemeSetting(isChecked)
        }


    }




}