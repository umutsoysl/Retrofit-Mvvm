package com.umut.campaings.service.api

import com.umut.campaings.model.Campaigns
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CampaignsApi {

    @GET("campaigns/{page}")
    fun getAllCampaignList(@Path("page") page: Int?): Call<Campaigns>?
}