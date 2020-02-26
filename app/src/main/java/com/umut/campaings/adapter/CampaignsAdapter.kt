package com.umut.campaings.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.umut.campaings.R
import com.umut.campaings.model.Banner
import com.umut.campaings.model.Campaigns
import com.umut.campaings.model.HotDeals


class CampaignsAdapter(context: Context, hotDeals: ArrayList<HotDeals>,banners: ArrayList<Banner>) :
    RecyclerView.Adapter<CampaignsAdapter.CampaignsViewHolder>() {
    var context: Context
    var bannerList : ArrayList<Banner> ? = ArrayList()
    var hotDealsList : ArrayList<HotDeals> ? = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CampaignsViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.campaign_list_item, parent, false)
        return CampaignsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CampaignsViewHolder,
        position: Int
    ) {
        holder.hotDealText.setText(hotDealsList!![position].title)
        holder.dateFullText.setText(hotDealsList!![position].expirationDate.toString())
        Picasso.get().load(bannerList!![position].image!!.url)
            .resize(bannerList!![position]!!.image!!.width!!, bannerList!![position]!!.image!!.height!!)
            .centerInside()
            .into(holder.ivCampaign)
    }

    override fun getItemCount(): Int {
        return bannerList!!.size
    }

    inner class CampaignsViewHolder(itemView: View) : ViewHolder(itemView) {
        var hotDealText: TextView
        var dateFullText: TextView
        var ivCampaign: ImageView

        init {
            hotDealText = itemView.findViewById(R.id.hotDealText)
            dateFullText = itemView.findViewById(R.id.dateFullText)
            ivCampaign = itemView.findViewById(R.id.ivCampaign)
        }
    }

    init {
        this.context = context
        this.bannerList = banners
        this.hotDealsList = hotDeals
    }
}