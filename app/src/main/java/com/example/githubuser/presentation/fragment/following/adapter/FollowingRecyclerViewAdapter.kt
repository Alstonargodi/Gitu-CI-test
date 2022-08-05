package com.example.githubuser.presentation.fragment.following.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.core.domain.model.UserFollowing
import com.example.githubuser.databinding.ItemcvFollowerBinding

class FollowingRecyclerViewAdapter(private var dataList : List<UserFollowing>)
    : RecyclerView.Adapter<FollowingRecyclerViewAdapter.ViewHolder>() {
    private lateinit var onItemClickDetail : OnItemClickDetil

    class ViewHolder(var binding : ItemcvFollowerBinding): RecyclerView.ViewHolder(binding.root)

    fun onItemCLickDetail(onClickDetail : OnItemClickDetil){
        this.onItemClickDetail = onClickDetail
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemcvFollowerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            NamefollowerTv.text = item.username

            Glide.with(holder.itemView.context)
                .asBitmap()
                .load(item.avatarUrl)
                .circleCrop()
                .into(ImgFollower)

            ImgFollower.setOnClickListener {
                onItemClickDetail.onItemClickDetail(item.username)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size


    interface OnItemClickDetil{
        fun onItemClickDetail(userName : String)
    }
}