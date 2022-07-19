package com.example.githubuser.presentation.fragment.githubrepository

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentRepoBinding
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject
import com.example.githubuser.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.githubuser.presentation.fragment.githubrepository.adapter.RepositoryRecyclerViewAdapter
import com.example.githubuser.presentation.fragment.favorite.FavoriteViewModel
import com.example.githubuser.presentation.utils.UtilViewModel
import com.example.githubuser.presentation.utils.viewmodelfactory.ViewModelFactory
import com.example.githubuser.viewmodel.util.obtainViewModel
import com.google.android.material.snackbar.Snackbar


class RepositoryFragment: Fragment() {
    private lateinit var repoBinding: FragmentRepoBinding

    private lateinit var adapter : RepositoryRecyclerViewAdapter

    private val repoViewModel : GithubRepositoryViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    private val favViewModel : FavoriteViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    private val utilViewModel by viewModels<UtilViewModel>()



    private var userName = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repoBinding = FragmentRepoBinding.inflate(layoutInflater)
        repoViewModel.isLoading.observe(viewLifecycleOwner){ isLoading(it) }
        userName = arguments?.getString("value","").toString()


        repoViewModel.apply {
            getUserRepo(userName)
            repoResponse.observe(viewLifecycleOwner){ responData ->
                showRepositoryList(responData)
            }
        }
        return repoBinding.root
    }

    private fun showRepositoryList(list: List<RepositoryUserResponseItem>){

        adapter = RepositoryRecyclerViewAdapter(list)
        val recView = repoBinding.Reporecview
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())
        utilViewModel.apply { if (adapter.itemCount== 0) setEmptys(true) else setEmptys(false) }
        emptyChecker()

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recView.layoutManager = LinearLayoutManager(requireContext())
        }

        adapter.onItemClickFav(object : RepositoryRecyclerViewAdapter.OnItemClickFavorite{
            override fun onItemClickFavorite(data: RepositoryUserResponseItem) {
               setFavoriteRepo(data)
            }
        })
    }

    private fun setFavoriteRepo(data : RepositoryUserResponseItem){
        val favTemp = FavoriteProject(
            data.name,
            data.description,
            data.language,
            true
        )

        favViewModel.insertFavoriteRepo(favTemp)
        Snackbar.make(repoBinding.root,"add ${data.name} as Favorite Repo",
            Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .setBackgroundTint(Color.rgb(0, 200, 151))
            .show()
    }

    private fun isLoading(isLoading:Boolean){
        repoBinding.Repoprogress.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun emptyChecker(){
        repoBinding.apply {
            utilViewModel.isEmpty.observe(viewLifecycleOwner){ isDataNotExist ->
                if (isDataNotExist == true){
                    emptyStatmentRepo.visibility = View.VISIBLE
                    "$userName never make a repo".also { emptyStatmentRepo.text = it }
                }
            }
        }
    }

}