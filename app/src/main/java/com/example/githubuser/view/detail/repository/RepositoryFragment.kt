package com.example.githubuser.view.detail.repository

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentRepoBinding
import com.example.githubuser.data.local.entity.FavoriteProject
import com.example.githubuser.data.remote.githubresponse.repository.RepoResponseItem
import com.example.githubuser.viewmodel.FavoriteViewModel
import com.example.githubuser.viewmodel.RepositorViewModel
import com.example.githubuser.viewmodel.util.UtilViewModel
import com.example.githubuser.viewmodel.util.obtainViewModel


class RepositoryFragment: Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : ReposRecviewAdapter
    private val repoViewModel by viewModels<RepositorViewModel>()
    private val utilViewModel by viewModels<UtilViewModel>()
    private lateinit var favViewModel : FavoriteViewModel


    private var userName = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(layoutInflater)
        repoViewModel.isLoading.observe(viewLifecycleOwner){ isLoading(it) }
        userName = arguments?.getString("value","").toString()

        favViewModel = obtainViewModel(requireActivity())

        repoViewModel.apply {
            getUserRepo(userName)
            repoResponse.observe(viewLifecycleOwner){ responData ->
                showRepositoryList(responData)
            }
        }
        return binding.root
    }

    private fun showRepositoryList(list: List<RepoResponseItem>){
        adapter = ReposRecviewAdapter(list)
        val recView = binding.Reporecview
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())
        utilViewModel.setEmptys(false)
        emptyChecker()

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recView.layoutManager = LinearLayoutManager(requireContext())
        }


        adapter.onItemClickFav(object : ReposRecviewAdapter.OnItemClickFavorite{
            override fun onItemClickFavorite(data: RepoResponseItem) {
               setFavoriteRepo(data)
            }
        })
    }

    private fun setFavoriteRepo(data : RepoResponseItem){
        val favTemp = FavoriteProject(
            data.name,
            data.description,
            data.language,
            true
        )
        favViewModel.insertFavoriteRepo(favTemp)
        Toast.makeText(context,"favoritbaru", Toast.LENGTH_SHORT).show()
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

    private fun emptyChecker(){
        binding.apply {
            utilViewModel.isEmpty.observe(viewLifecycleOwner){ isDataNotExist ->
                if (isDataNotExist == true){
                    emptyStatmentRepo.visibility = View.VISIBLE
                    "$userName never make a repo".also { emptyStatmentRepo.text = it }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}