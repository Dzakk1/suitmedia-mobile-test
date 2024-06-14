package com.example.suitmedia_mobile_test.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.suitmedia_mobile_test.data.response.DataItem
import com.example.suitmedia_mobile_test.data.response.UserResponse
import com.example.suitmedia_mobile_test.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreenViewModel : ViewModel() {
    private val _user = MutableLiveData<List<DataItem>>()
    val user : LiveData<List<DataItem>> = _user


    init {
        getUser(1)
    }

    private fun getUser(page: Int) {
        val client = ApiConfig.getApiService().getUsers(page)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                val responseBody = response.body()

                responseBody?. let{
                    val currentUsers = _user.value ?: emptyList()
                    val allUser = currentUsers + it.data
                    _user.value= allUser

                    if (page < it.totalPages) {
                        getUser(page + 1)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("ThridScreenActivity", "Fail : ${t.message.toString()}")
            }
        })
    }
}