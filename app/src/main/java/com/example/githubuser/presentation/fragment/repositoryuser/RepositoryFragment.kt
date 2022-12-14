package com.example.githubuser.presentation.fragment.repositoryuser

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
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.remote.apiresponse.coderepository.RepositoryUserResponseItem
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.UserRepository
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.presentation.fragment.repositoryuser.adapter.RepositoryRecyclerViewAdapter
import com.example.githubuser.presentation.utils.UtilViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment: Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter : RepositoryRecyclerViewAdapter

    private val repositoryViewModel : GithubRepositoryViewModel by viewModels()

    private var userName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(layoutInflater)
        repositoryViewModel.isLoading.observe(viewLifecycleOwner){ isLoading(it) }
        userName = arguments?.getString("value","").toString()

        repositoryViewModel.apply {
            getUserRepository(userName).observe(viewLifecycleOwner){ response ->
                when(response){
                    is Resource.Loading ->{
                        binding.Repoprogress.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        response.data?.let {
                            showRepositoryList(it)
                        }
                        binding.Repoprogress.visibility = View.GONE
                    }
                    is Resource.Error ->{
                        binding.Repoprogress.visibility = View.GONE
                    }
                }
            }
        }

        return binding.root
    }

    private fun showRepositoryList(list: List<UserRepository>){
        adapter = RepositoryRecyclerViewAdapter(list)
        val recView = binding.Reporecview
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recView.layoutManager = LinearLayoutManager(requireContext())
        }

        adapter.onItemClickFav(object : RepositoryRecyclerViewAdapter.OnItemClickFavorite{
            override fun onItemClickFavorite(data: UserRepository) {
               setFavoriteRepo(data)
            }
        })
    }

    private fun setFavoriteRepo(data : UserRepository){
        val favTemp = FavoriteProject(
            data.name,
            data.description,
            data.language,
            true
        )

        repositoryViewModel.insertFavoriteRepo(favTemp)
        Snackbar.make(binding.root,"add ${data.name} as Favorite Repo",
            Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .setBackgroundTint(Color.rgb(0, 200, 151))
            .show()
    }

    private fun isLoading(isLoading:Boolean){
        binding.Repoprogress.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}