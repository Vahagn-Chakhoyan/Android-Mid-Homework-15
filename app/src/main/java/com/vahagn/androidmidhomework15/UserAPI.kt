package com.vahagn.androidmidhomework15

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserApi {
    @GET("public-api/products/{id}")
    fun getSingleProduct(@Path("id") id :  Int): Call<UserModel>
}


object UserRetrofitService  {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://gorest.co.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
