package com.example.githubuser.presentation.fragment.repositoryuser.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.R
import com.example.githubuser.databinding.ItemcvRepositoryBinding
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.core.domain.model.UserRepository

class RepositoryRecyclerViewAdapter(private var dataList : List<UserRepository>):
    RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder>() {
    private lateinit var onItemClickFav : OnItemClickFavorite

    class ViewHolder(var binding : ItemcvRepositoryBinding): RecyclerView.ViewHolder(binding.root)

    fun onItemClickFav(onItemClickFav : OnItemClickFavorite){
        this.onItemClickFav = onItemClickFav
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemcvRepositoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            item.apply {
                TitleTvrepo.text = name
                AboutTvrepo.text = description
                LangTvrepo.text = language
                StarTvrepo.text = stargazersCount.toString()
                ForkTvrepo.text = forksCount.toString()

                if (stargazersCount == 0 ){ StarTvrepo.visibility = View.INVISIBLE }
                if (forksCount == 0){ ForkTvrepo.visibility = View.INVISIBLE }

                FavrepoBtn.setOnClickListener {
                    FavrepoBtn.setImageDrawable(
                        ContextCompat.getDrawable(FavrepoBtn.context,R.drawable.ic_baseline_star_full)
                    )
                    onItemClickFav.onItemClickFavorite(item)
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickFavorite{
        fun onItemClickFavorite(data : UserRepository)
    }


}