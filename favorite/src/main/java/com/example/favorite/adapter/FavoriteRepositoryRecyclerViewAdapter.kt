package com.example.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.R
import com.example.githubuser.databinding.ItemcvRepositoryBinding
import com.example.core.data.local.entity.favoriteproject.FavoriteProject

class FavoriteRepositoryRecyclerViewAdapter(private val favList : List<FavoriteProject>):
    RecyclerView.Adapter<FavoriteRepositoryRecyclerViewAdapter.ViewHolder>(){
    private lateinit var onItemClickDelete : OnItemClickDelete

    class ViewHolder(var binding : ItemcvRepositoryBinding): RecyclerView.ViewHolder(binding.root)

    fun setOnItemDeleted(onItemDelete : OnItemClickDelete){
        this.onItemClickDelete = onItemDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemcvRepositoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favList[position]

        holder.binding.apply {
            TitleTvrepo.text = item.name
            LangTvrepo.text = item.language
            AboutTvrepo.text = item.description


            View.INVISIBLE.apply {
                StarTvrepo.visibility = this
                ForkTvrepo.visibility = this
            }

            if(item.isSaved) FavrepoBtn.setImageDrawable(
                ContextCompat.getDrawable(FavrepoBtn.context,R.drawable.ic_baseline_star_full)
            )

            FavrepoBtn.setOnClickListener {
                FavrepoBtn.setImageDrawable(
                    ContextCompat.getDrawable(FavrepoBtn.context,
                        R.drawable.ic_baseline_star_outline_24
                    )
                )
                onItemClickDelete.onItemClickDelete(item)
            }
        }
    }

    override fun getItemCount(): Int  = favList.size


    interface OnItemClickDelete{
        fun onItemClickDelete(data : FavoriteProject)
    }
}