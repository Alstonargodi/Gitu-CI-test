package com.example.githubuser.view.home.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.UserhomCvBinding
import com.example.githubuser.model.githubresponse.DetailResponse
import com.example.githubuser.model.githubresponse.ItemsItem
import com.example.githubuser.model.githubresponse.ListResponse
import com.example.githubuser.view.home.HomeFragmentDirections

class UserListRecAdapter(private var dataList : List<ItemsItem>): RecyclerView.Adapter<UserListRecAdapter.ViewHolder>() {

    class ViewHolder(var binding : UserhomCvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(UserhomCvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.apply {
            NamaTvcard.text = data.login.toString()

            Glide.with(holder.itemView.context)
                .asDrawable()
                .load(data.avatarUrl)
                .circleCrop()
                .into(AvatarCvuser)

            AvatarCvuser.setOnClickListener {
                holder.itemView.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.login))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}