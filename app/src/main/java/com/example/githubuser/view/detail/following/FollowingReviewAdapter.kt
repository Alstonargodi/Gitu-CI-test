package com.example.githubuser.view.detail.following


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.data.remote.githubresponse.follow.FollowResponseItem
import com.example.githubuser.databinding.ItemcvFollowerBinding

class FollowingReviewAdapter(private var dataList : List<FollowResponseItem>)
    : RecyclerView.Adapter<FollowingReviewAdapter.ViewHolder>() {
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
            NamefollowerTv.text = item.login

            Glide.with(holder.itemView.context)
                .asBitmap()
                .load(item.avatarUrl)
                .circleCrop()
                .into(ImgFollower)

            ImgFollower.setOnClickListener {
                onItemClickDetail.onItemClickDetail(item.login)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size


    interface OnItemClickDetil{
        fun onItemClickDetail(userName : String)
    }
}