package com.example.githubuser.view.detail.repository


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.databinding.ItemcvRepositoryBinding
import com.example.githubuser.data.remote.githubresponse.repository.RepoResponseItem

class ReposRecviewAdapter(private var dataList : List<RepoResponseItem>):
    RecyclerView.Adapter<ReposRecviewAdapter.ViewHolder>() {
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

                FavrepoBtn.setOnClickListener { onItemClickFav.onItemClickFavorite(item) }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickFavorite{
        fun onItemClickFavorite(data : RepoResponseItem)
    }


}