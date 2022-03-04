package com.example.githubuser.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FollowerItemcvBinding
import com.example.githubuser.model.githubresponse.follower.FollowerResponseItem

import com.example.githubuser.viewmodel.MainViewModel

class FollowerRecviewAdapter(private var dataList :List<FollowerResponseItem>): RecyclerView.Adapter<FollowerRecviewAdapter.ViewHolder>() {


    class ViewHolder(var binding : FollowerItemcvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FollowerItemcvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
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
        return dataList.size
    }

}