package com.example.githubuser.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.FollowerItemcvBinding
import com.example.githubuser.model.githubresponse.follower.FollowerResponseItem
import com.example.githubuser.model.githubresponse.following.FollowingResponseItem

class FollowingRecviewAdapter:RecyclerView.Adapter<FollowingRecviewAdapter.ViewHolder>() {
    var datalist = emptyList<FollowingResponseItem>()

    class ViewHolder(var binding : FollowerItemcvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FollowerItemcvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datalist[position]
        holder.binding.apply {
            NamefollowerTv.text = item.login

            Glide.with(holder.itemView.context)
                .asBitmap()
                .load(item.avatarUrl)
                .circleCrop()
                .into(ImgFollower)
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setData(list: List<FollowingResponseItem>){
        datalist = list
        notifyDataSetChanged()
    }
}