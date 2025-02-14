package com.example.suitmedia_mobile_test.data.retrofit

import com.example.suitmedia_mobile_test.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(
        @Query("page") page: Int?,
    ): Call<UserResponse>
}