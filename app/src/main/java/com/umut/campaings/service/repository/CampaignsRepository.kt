package com.umut.campaings.service.repository

import androidx.lifecycle.MutableLiveData
import com.umut.campaings.model.Campaigns
import com.umut.campaings.service.api.CampaignsApi
import com.umut.campaings.service.util.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class CampaignsRepository {

    private var campaignsApi: CampaignsApi? = null

    constructor() {
        var service: RetrofitService ? = RetrofitService()
        campaignsApi = service!!.createService(CampaignsApi::class.java)
    }

    fun getCampaigns(page:Int): MutableLiveData<Campaigns>? {

        var service: RetrofitService ? = RetrofitService()
        campaignsApi = service!!.createService(CampaignsApi::class.java)

        val campaignsData: MutableLiveData<Campaigns> = MutableLiveData()
        campaignsApi!!.getAllCampaignList(page)!!.enqueue(object : Callback<Campaigns> {
            override fun onResponse(
                call: Call<Campaigns>?,
                response: Response<Campaigns>
            ) {
                if (response.isSuccessful()) {
                    campaignsData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<Campaigns>?, t: Throwable?) {
                campaignsData.setValue(null)
            }
        })
        return campaignsData
    }

}