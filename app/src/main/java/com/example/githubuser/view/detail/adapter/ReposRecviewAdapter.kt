package com.example.githubuser.view.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.FollowerItemcvBinding
import com.example.githubuser.databinding.RepositoryItemcvBinding
import com.example.githubuser.model.githubresponse.follower.FollowerResponseItem
import com.example.githubuser.model.githubresponse.repository.RepoResponseItem

class ReposRecviewAdapter:  RecyclerView.Adapter<ReposRecviewAdapter.ViewHolder>() {

    var datalist = emptyList<RepoResponseItem>()

    class ViewHolder(var binding : RepositoryItemcvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RepositoryItemcvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datalist[position]
        holder.binding.apply {
            item.apply {
                TitleTvrepo.text = name
                AboutTvrepo.text = description
                LangTvrepo.text = language
                StarTvrepo.text = stargazersCount.toString()
                ForkTvrepo.text = forksCount.toString()

                if (stargazersCount == 0 ){ StarTvrepo.visibility = View.INVISIBLE }
                if (forksCount == 0){ ForkTvrepo.visibility = View.INVISIBLE }
            }
        }
    }

    override fun getItemCount(): Int {
       return datalist.size
    }

    fun setData(list: List<RepoResponseItem>){
        datalist = list
        notifyDataSetChanged()
    }
}