package com.umut.campaings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umut.campaings.model.Campaigns
import com.umut.campaings.service.repository.CampaignsRepository


class CampaignsViewModel : ViewModel() {

     var mutableLiveData: MutableLiveData<Campaigns>? = null
     var newsRepository: CampaignsRepository? = null

    fun init(page:Int) {
        if (mutableLiveData != null) {
            return
        }
        newsRepository = CampaignsRepository()
        mutableLiveData = newsRepository!!.getCampaigns(page)
    }

    fun getCampaignRepository(): LiveData<Campaigns>? {
        return mutableLiveData
    }
}