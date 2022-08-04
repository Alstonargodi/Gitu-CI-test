package com.example.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.githubuser.R
import com.example.favorite.databinding.ItemcvFavoritepeopleBinding

class FavoriteUserRecyclerViewAdapter(private val favList : List<FavoriteUser>):
    RecyclerView.Adapter<FavoriteUserRecyclerViewAdapter.ViewHolder>(){
    private lateinit var onItemClickDetail : OnItemClickDetail
    private lateinit var onItemClickDelete : OnItemDelete

    class ViewHolder(var binding : ItemcvFavoritepeopleBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnItemCallBack(onItemClickDetail: OnItemClickDetail){
        this.onItemClickDetail = onItemClickDetail
    }

    fun setOnItemDeleted(onItemDelete : OnItemDelete){
        this.onItemClickDelete = onItemDelete
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemcvFavoritepeopleBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = favList[position]
        holder.binding.apply {
            NameFavpeoplecv.text = item.name
            locationFavpeoplecv.text = item.location
            companFavpeoplecv.text = item.company

            imageView.setOnClickListener { onItemClickDetail.onItemClick(item)}

            btnfav.setOnClickListener { onItemClickDelete.onItemClickDelete(item) }


            Glide.with(holder.itemView.context)
                .load(item.imageLink)
                .circleCrop()
                .into(imageView)

            btnfav.setImageDrawable(
                ContextCompat.getDrawable(
                    btnfav.context,R.drawable.ic_baseline_favorite_full
                )
            )
        }

    }

    override fun getItemCount(): Int = favList.size

    interface OnItemClickDetail{
        fun onItemClick(data : FavoriteUser)
    }
    interface OnItemDelete{
        fun onItemClickDelete(data : FavoriteUser)
    }
}