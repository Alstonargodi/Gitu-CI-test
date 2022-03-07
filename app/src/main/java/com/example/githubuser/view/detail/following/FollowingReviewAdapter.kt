package com.example.githubuser.view.detail.following


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FollowerItemcvBinding
import com.example.githubuser.model.githubresponse.following.FollowingResponseItem
import com.example.githubuser.view.detail.DetailFragment

class FollowingReviewAdapter(private var dataList : List<FollowingResponseItem>):RecyclerView.Adapter<FollowingReviewAdapter.ViewHolder>() {


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

            ImgFollower.setOnClickListener {
                val fmanager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val testfragment = DetailFragment()
                val mbundle = Bundle()
                mbundle.putString("userName",item.login)

                testfragment.arguments = mbundle

                fmanager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView,testfragment, DetailFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}