package com.example.githubuser.viewmodel.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UtilViewModel: ViewModel() {

    private val _isEmpty = MutableLiveData(true)
    val isEmpty : LiveData<Boolean> = _isEmpty
    fun setEmptys(status : Boolean){
        _isEmpty.value = status
    }

    private val _textQuery = MutableLiveData<String>()
    val textQuery : LiveData<String> = _textQuery
    fun saveText(text: String){
        _textQuery.value = text
    }



}