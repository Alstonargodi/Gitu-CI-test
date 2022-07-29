package com.example.githubuser.presentation.fragment.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.ItemcvUserlisthomeBinding
import com.example.githubuser.core.domain.model.ListUser
import com.example.githubuser.presentation.fragment.home.HomeFragmentDirections

class UserListRecAdapter(private var dataList : List<ListUser>): RecyclerView.Adapter<UserListRecAdapter.ViewHolder>() {

    class ViewHolder(var binding : ItemcvUserlisthomeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemcvUserlisthomeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.apply {
            NamaTvcard.text = data.name

            Glide.with(holder.itemView.context)
                .asDrawable()
                .load(data.imageLink)
                .circleCrop()
                .into(AvatarCvuser)

            holder.itemView.setOnClickListener {
                holder.itemView.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.name))
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

}