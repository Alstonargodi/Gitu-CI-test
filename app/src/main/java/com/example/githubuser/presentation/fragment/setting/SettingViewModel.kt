package com.example.githubuser.presentation.fragment.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.local.preference.SettingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class SettingViewModel(
    private val pref : SettingPreferences
): ViewModel() {

    fun getThemeSetting(): LiveData<Boolean> = pref.getThemesSetting().asLiveData()

    fun setThemeSetting(isDarkMode : Boolean){
        viewModelScope.launch {
            pref.setThemeSetting(isDarkMode)
        }
    }

}