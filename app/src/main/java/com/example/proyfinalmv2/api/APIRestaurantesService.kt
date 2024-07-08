package com.example.proyfinalmv2.api

import com.example.proyfinalmv2.models.LogReg.Loginuser
import com.example.proyfinalmv2.models.LogReg.Registeruser
import com.example.proyfinalmv2.models.LogReg.Token
import com.example.proyfinalmv2.models.Rest.RestSnFilt
import com.example.proyfinalmv2.models.Rest.RestSnFilts

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIRestaurantesService {
    // Inicio de sesion y registro //
    // Loginuser //
    @POST("loginuser")
    fun insertloginuser(
        @Body loginuser:
        Loginuser
    ): Call<Token>

    // Registeruser //
    @POST("registeruser")
    fun insertregisteruser(
        @Body registeruser:
        Registeruser
    ): Call<Registeruser>

    // Restaurante //
    // RestSnFilt
    @POST("restaurants/search")
    fun insertrestsnfilt(
    ): Call<RestSnFilts>
}