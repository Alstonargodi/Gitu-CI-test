package com.example.githubuser.view.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemcvUserlisthomeBinding
import com.example.githubuser.data.remote.githubresponse.ItemsItem
import com.example.githubuser.view.home.HomeFragmentDirections

class UserListRecAdapter(private var dataList : List<ItemsItem>): RecyclerView.Adapter<UserListRecAdapter.ViewHolder>() {

    class ViewHolder(var binding : ItemcvUserlisthomeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemcvUserlisthomeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.apply {
            NamaTvcard.text = data.login

            Glide.with(holder.itemView.context)
                .asDrawable()
                .load(data.avatarUrl)
                .circleCrop()
                .into(AvatarCvuser)

            holder.itemView.setOnClickListener {
                holder.itemView.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.login))
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

}