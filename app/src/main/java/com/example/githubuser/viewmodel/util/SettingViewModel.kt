package com.example.githubuser.viewmodel.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubuser.data.SettingPreferences
import kotlinx.coroutines.launch

class SettingViewModel(private val pref : SettingPreferences): ViewModel() {

    fun getThemeSetting(): LiveData<Boolean> = pref.getThemesSetting().asLiveData()

    fun setThemeSetting(isDarkMode : Boolean){
        viewModelScope.launch {
            pref.setThemeSetting(isDarkMode)
        }
    }

}