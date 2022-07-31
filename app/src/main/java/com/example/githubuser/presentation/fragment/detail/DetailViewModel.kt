package com.example.githubuser.presentation.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.remote.apiresponse.detail.DetailUserResponse
import com.example.core.domain.remote.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: RemoteUseCase
): ViewModel() {
    companion object{
        const val TAG = "DetailViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> = _errorResponse

    private val _detailUserResponse = MutableLiveData<DetailUserResponse>()
    val detailUserResponse : LiveData<DetailUserResponse> = _detailUserResponse

    fun getUserDetail(name : String): LiveData<DetailUserResponse>{
        return repository.getUserDetail(name)
    }
}