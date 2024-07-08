package com.example.proyfinalmv2.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyfinalmv2.databinding.LyrestBinding
import com.example.proyfinalmv2.models.Rest.RestSnFilt
import com.example.proyfinalmv2.models.Rest.RestSnFilts

class RestAdapter(
    private val restinv: RestSnFilts,
    private val listener: OnRestInvListener
): RecyclerView.Adapter<RestAdapter.RestSnInvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestSnInvViewHolder {
        val binding = LyrestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RestSnInvViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RestSnInvViewHolder, position: Int) {
        val restinv = restinv[position]
        holder.bind(restinv, listener)
    }

    override fun getItemCount(): Int {
        return restinv.size
    }

    class RestSnInvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(restinv: RestSnFilt, listener: OnRestInvListener){
            val binding = LyrestBinding.bind(itemView)
            binding.txtNameRI.text = restinv.name
            binding.txtCityRi.text = restinv.city
            Glide.with(itemView.context)
                .load(restinv.logo)
                .into(binding.imgLogoRI)
        }
    }

    fun updateData(restSnFilts: RestSnFilts?) {
        restSnFilts?.let {
            restinv.clear()
            restinv.addAll(restSnFilts)
            notifyDataSetChanged()
        }
    }

    // -----------------------------------------//
    interface OnRestInvListener {
        fun onRestInvClick(restinv: RestSnFilt)
    }


}