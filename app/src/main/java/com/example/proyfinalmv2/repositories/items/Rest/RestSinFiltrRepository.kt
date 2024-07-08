package com.example.proyfinalmv2.repositories.items.Rest

import com.example.proyfinalmv2.api.APIRestaurantesService
import com.example.proyfinalmv2.models.Rest.RestSnFilts
import com.example.proyfinalmv2.repositories.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object RestSinFiltrRepository {
    fun searchRest(
        id: Int,
        success: (RestSnFilts?) -> Unit,
        failure: (Throwable) -> Unit
    ){
        val retrofit = RetrofitBuilder.getRetrofitBuilderWows()

        val service: APIRestaurantesService =
            retrofit.create(APIRestaurantesService::class.java)

        service.insertrestsnfilt().enqueue(
            object : Callback<RestSnFilts>{
                override fun onResponse(
                    call: Call<RestSnFilts>,
                    response: Response<RestSnFilts>
                ) {
                    val restinv = response.body()
                    success(restinv)
                }
                override fun onFailure(call: Call<RestSnFilts>, t: Throwable) {
                    failure(t)
                }
            }
        )
    }
}