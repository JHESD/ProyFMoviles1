package com.example.proyfinalmv2.repositories.items.LogReg

import com.example.proyfinalmv2.api.APIRestaurantesService
import com.example.proyfinalmv2.models.LogReg.Loginuser
import com.example.proyfinalmv2.models.LogReg.Token
import com.example.proyfinalmv2.repositories.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LoginuserRepository {
    fun doLogin(
        email: String,
        password: String,
        success: (Token?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit =
            RetrofitBuilder.getRetrofitBuilderWows()

        val service: APIRestaurantesService =
            retrofit.create(APIRestaurantesService::class.java)
        service.insertloginuser(
            Loginuser(
                email,
                password
            ))
            .enqueue(object:
                Callback<Token> {
                override fun onResponse(
                    res: Call<Token>,
                    response: Response<Token>
                ) {
                    if (response.code() == 401) {
                        success(null)
                        return
                    }
                    val loginResponse = response.body()
                    success(loginResponse)
                }

                override fun onFailure(res: Call<Token>, t: Throwable) {
                    failure(t)
                }
            })
    }
}