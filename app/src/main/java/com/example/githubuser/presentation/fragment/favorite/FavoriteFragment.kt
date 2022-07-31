package com.example.githubuser.presentation.fragment.favorite

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.core.data.local.entity.userlist.GithubUserList
import com.example.core.data.local.entity.githubrepository.GithubRepositoryList
import com.example.githubuser.presentation.fragment.book.BookFragmentDirections
import com.example.githubuser.presentation.fragment.favorite.adapter.FavoriteUserRecyclerViewAdapter
import com.example.githubuser.presentation.fragment.favorite.adapter.FavoriteRepositoryRecyclerViewAdapter
import com.example.githubuser.databinding.FragmentFavoriteBinding
import com.example.githubuser.presentation.fragment.favorite.viewmodel.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var peopleRecyclerViewAdapter : FavoriteUserRecyclerViewAdapter
    private lateinit var repositoryRecyclerViewAdapter : FavoriteRepositoryRecyclerViewAdapter

    private val favViewModel : FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        favViewModel.isLoading.observe(viewLifecycleOwner){ respon ->
            isLoading(respon)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView(){
        when(arguments?.getInt("number")){
            1 -> showFavRepository()
            2 -> showFavPeople()
        }
    }

    private fun showFavPeople(){
        favViewModel.apply {
            readFavoritePeople()
            responGithubUserList.observe(viewLifecycleOwner){ respon ->
                if (respon.isNullOrEmpty()) true.isEmpty()
                peopleRecyclerViewAdapter = FavoriteUserRecyclerViewAdapter(respon)
                binding.RecyclerVFavorite.adapter = peopleRecyclerViewAdapter
                binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())

                peopleRecyclerViewAdapter.apply {
                    setOnItemCallBack(object : FavoriteUserRecyclerViewAdapter.OnItemClickDetail{
                        override fun onItemClick(data: GithubUserList) {
                            findNavController().navigate(
                                BookFragmentDirections.actionAuthorFragmentToDetailFragment(data.name)
                            )
                        }
                    })
                    setOnitemDelete(object : FavoriteUserRecyclerViewAdapter.OnItemDelete{
                        override fun onItemClickDelete(data: GithubUserList) {
                            deleteFavPeople(data)
                        }
                    })
                }
            }
        }
    }

    private fun showFavRepository(){
        favViewModel.apply {
            readFavoriteProject()
            responFavoriteRepo.observe(viewLifecycleOwner){respon ->
                if (respon.isNullOrEmpty()) true.isEmpty()
                repositoryRecyclerViewAdapter = FavoriteRepositoryRecyclerViewAdapter(respon)
                binding.RecyclerVFavorite.adapter = repositoryRecyclerViewAdapter
                binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())


                repositoryRecyclerViewAdapter.setOnitemDelete(object : FavoriteRepositoryRecyclerViewAdapter.OnItemClickDelete{
                    override fun onItemClickDelete(data: GithubRepositoryList) {
                        deleteFavProject(data)
                    }
                })
            }

        }
    }

    private fun deleteFavPeople(githubUserList: GithubUserList){
        showSnackbar(githubUserList.name)
        favViewModel.deletePersonFavoritePeople(githubUserList.name)
    }

    private fun deleteFavProject(githubRepositoryList: GithubRepositoryList){
        showSnackbar(githubRepositoryList.name)
        favViewModel.deleteFavoriteRepo(githubRepositoryList)
    }

    private fun showSnackbar(name : String){
        Snackbar.make(binding.root,"Remove $name ",
            Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .setBackgroundTint(Color.rgb(137, 15, 13))
            .show()
    }

    private fun isLoading(isLoading:Boolean){
        binding.pgbarFavorite.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun Boolean.isEmpty() {
        if (this){
            binding.emptyviewfav.apply {
                layoutemptyview.visibility = View.VISIBLE
                tvResultempty.text = getString(R.string.emptyviewfav)
            }
        }
    }

}