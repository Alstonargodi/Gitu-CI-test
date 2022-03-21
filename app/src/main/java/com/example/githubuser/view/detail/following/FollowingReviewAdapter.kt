package com.example.githubuser.view.detail.following


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemcvFollowerBinding
import com.example.githubuser.data.remote.githubresponse.following.FollowingResponseItem
import com.example.githubuser.view.detail.DetailFragmentDirections

class FollowingReviewAdapter(private var dataList : List<FollowingResponseItem>):RecyclerView.Adapter<FollowingReviewAdapter.ViewHolder>() {


    class ViewHolder(var binding : ItemcvFollowerBinding): RecyclerView.ViewHolder(binding.root)

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
                holder.itemView.findNavController()
                    .navigate(DetailFragmentDirections.actionDetailFragmentSelf(item.login))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}