package com.example.githubuser

import android.util.Log

import com.example.githubuser.ViewModelTest.ValidationResult.tag
import com.example.githubuser.ViewModelTest.ValidationResult.validateData
import com.example.githubuser.data.remote.githubresponse.ItemsItem
import com.example.githubuser.data.remote.githubresponse.ListResponse
import com.example.githubuser.data.remote.service.ApiService
import com.example.githubuser.viewmodel.HomeViewModel
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
        fun validateData(item:List<ItemsItem>):Boolean{
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
        apiService.getUserList(dummyName).enqueue(object : Callback<ListResponse>{
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                assertEquals(true, response.body()?.items?.let { validateData(it) })
            }
            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                Log.d(tag,t.message.toString())
            }
        })
    }

}