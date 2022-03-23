package com.example.githubuser.view.detail.follower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemcvFollowerBinding
import com.example.githubuser.data.remote.githubresponse.follower.FollowerResponseItem

class FollowerRecviewAdapter(private var dataList :List<FollowerResponseItem>)
    : RecyclerView.Adapter<FollowerRecviewAdapter.ViewHolder>() {

    private lateinit var onItemClickDetail : OnItemCallDetail

    class ViewHolder(var binding : ItemcvFollowerBinding): RecyclerView.ViewHolder(binding.root)

    fun onItemClickDetail(onItemClickDetail : OnItemCallDetail){
        this.onItemClickDetail = onItemClickDetail
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemcvFollowerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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

            ImgFollower.setOnClickListener {
                onItemClickDetail.onItemCallDetail(item.login)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemCallDetail{
        fun onItemCallDetail(username: String)
    }


}