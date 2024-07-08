package com.example.proyfinalmv2.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    // URL BASE
    fun getRetrofitBuilderWows(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://restaurantes.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}