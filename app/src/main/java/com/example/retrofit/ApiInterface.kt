package com.example.retrofit

import com.example.model.DataItems
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("posts")
    fun postEmpDetails(): Call<List<DataItems>>

    @POST("posts/api/v1/create")
    suspend fun createEmployee(@Body requestBody: RequestBody): Response<ResponseBody>
}