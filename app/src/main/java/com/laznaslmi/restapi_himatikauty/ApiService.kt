package com.laznaslmi.restapi_himatikauty

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<ArrayList<ResponseModelItem>>
}