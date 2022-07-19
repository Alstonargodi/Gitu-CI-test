package com.example.githubuser.presentation.fragment.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.R
import com.example.githubuser.data.local.preference.SettingPreferences
import com.example.githubuser.databinding.FragmentSettingBinding
import com.example.githubuser.presentation.utils.viewmodelfactory.SettingViewModelFactory


private val Context.datastore : DataStore<Preferences> by preferencesDataStore("theme_setting")

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
                binding.ImgviewMode.setImageDrawable(
                    ContextCompat.getDrawable(this,R.drawable.ic_moondark)
                )
                binding.spinnerMode.prompt = "Current Mode is Dark"
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.ImgviewMode.setImageDrawable(
                    ContextCompat.getDrawable(this,R.drawable.ic_daylight)
                )
                binding.spinnerMode.prompt = "Current Mode is Light"
            }
        }

        binding.spinnerMode.onItemSelectedListener = object : AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p0?.getItemAtPosition(p2)){
                    "Dark"->setViewModel.setThemeSetting(true)
                    "Light"->setViewModel.setThemeSetting(false)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                setViewModel.setThemeSetting(false)
            }
        }

    }

}