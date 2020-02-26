package com.umut.campaings.service.util

import com.umut.campaings.utils.Constants.Companion.API_HOST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class RetrofitService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


   open fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}