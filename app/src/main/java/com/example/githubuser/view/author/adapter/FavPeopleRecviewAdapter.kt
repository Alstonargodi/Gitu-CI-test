package com.example.githubuser.view.author.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FavpeopleItemcvBinding
import com.example.githubuser.local.entity.FavoritePeople

class FavPeopleRecviewAdapter(private val favList : List<FavoritePeople>):
    RecyclerView.Adapter<FavPeopleRecviewAdapter.ViewHolder>(){
    private lateinit var onItemCallBack : OnItemCallBack

    class ViewHolder(var binding : FavpeopleItemcvBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnItemCallBack(onItemCallBack: OnItemCallBack){
        this.onItemCallBack = onItemCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(FavpeopleItemcvBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = favList[position]
        holder.binding.apply {
            NameFavpeoplecv.text = item.name

            imageView.setOnClickListener { onItemCallBack.onItemClick(item)}

            Glide.with(holder.itemView.context)
                .load(item.imageLink)
                .into(imageView)

            if (item.isSaved){
                btnfav.setImageDrawable(
                    ContextCompat.getDrawable(
                        btnfav.context,R.drawable.ic_baseline_favorite_full
                    )
                )
            }
        }

    }

    override fun getItemCount(): Int = favList.size

    interface OnItemCallBack{
        fun onItemClick(data : FavoritePeople)
    }
}