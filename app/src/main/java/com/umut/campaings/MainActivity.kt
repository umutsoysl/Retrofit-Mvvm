package com.umut.campaings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umut.campaings.adapter.CampaignsAdapter
import com.umut.campaings.model.Banner
import com.umut.campaings.model.Campaigns
import com.umut.campaings.model.HotDeals
import com.umut.campaings.viewmodels.CampaignsViewModel

class MainActivity : AppCompatActivity() {

    var campaignList : ArrayList<Campaigns> ? = ArrayList()
    var bannerList : ArrayList<Banner> ? = ArrayList()
    var hotDealsList : ArrayList<HotDeals> ? = ArrayList()
    var campaignsAdapter : CampaignsAdapter ? = null
    var campaignRecyclerList : RecyclerView? = null
    var campaignsViewModel : CampaignsViewModel? = null
    var page:Int ? = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        campaignRecyclerList = findViewById(R.id.campaignList)

        campaignsViewModel = ViewModelProviders.of(this)[CampaignsViewModel::class.java]
        campaignsViewModel!!.init(page!!)

        campaignsViewModel!!.getCampaignRepository()!!.observe(this, Observer {

                campaigns -> campaignList!!.add(campaigns)

                createAllList(campaignList!!)
                campaignsAdapter!!.notifyDataSetChanged()
        })

        setupRecyclerView()
    }

    private fun createAllList(campaignList : ArrayList<Campaigns>){
        for( item in campaignList){
            bannerList!!.addAll(item!!.banners!!)
            hotDealsList!!.addAll(item!!.hotDeals!!)
        }
    }

    private fun setupRecyclerView() {
        if (campaignsAdapter == null) {
            campaignsAdapter = CampaignsAdapter(this@MainActivity, hotDealsList!!,bannerList!!)
            campaignRecyclerList!!.layoutManager = LinearLayoutManager(this)
            campaignRecyclerList!!.adapter = campaignsAdapter
            campaignRecyclerList!!.itemAnimator = DefaultItemAnimator()
            campaignRecyclerList!!.isNestedScrollingEnabled = true
        } else {
            campaignsAdapter!!.notifyDataSetChanged()
        }
    }
}
