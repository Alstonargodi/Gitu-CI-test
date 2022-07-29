package com.example.githubuser

import android.util.Log

import com.example.githubuser.ViewModelTest.ValidationResult.tag
import com.example.githubuser.ViewModelTest.ValidationResult.validateData
import com.example.core.data.remote.apiresponse.ListUserResponseItem
import com.example.core.data.remote.apiresponse.ListUserResponse
import com.example.core.data.remote.apiservice.ApiService
import com.example.githubuser.presentation.fragment.home.HomeViewModel
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModelTest {
    private lateinit var homeViewModel: HomeViewModel
    private val dummyName = "budi"

    object ValidationResult{
        fun validateData(item:List<ListUserResponseItem>):Boolean{
            if (item[0].login.isNotEmpty()) return true
            return false
        }
        const val tag = "Viewmodeltest"
    }

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun before(){
        homeViewModel = HomeViewModel()
    }

    @Test
    fun apiServiceTest(){
        apiService.getUserList(dummyName).enqueue(object : Callback<ListUserResponse>{
            override fun onResponse(call: Call<ListUserResponse>, response: Response<ListUserResponse>) {
                assertEquals(true, response.body()?.items?.let { validateData(it) })
            }
            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                Log.d(tag,t.message.toString())
            }
        })
    }

}