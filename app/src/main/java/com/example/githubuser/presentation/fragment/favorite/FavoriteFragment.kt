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
import com.example.githubuser.databinding.FragmentFavoriteBinding
import com.example.githubuser.data.local.entity.userlist.GithubListUser
import com.example.githubuser.data.local.entity.favoriteproject.FavoriteProject
import com.example.githubuser.presentation.fragment.book.BookFragmentDirections
import com.example.githubuser.presentation.fragment.favorite.adapter.FavoriteUserRecyclerViewAdapter
import com.example.githubuser.presentation.fragment.favorite.adapter.FavoriteRepositoryRecyclerViewAdapter
import com.example.githubuser.presentation.utils.viewmodelfactory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class FavoriteFragment : Fragment() {
    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var peopleRecviewAdapter : FavoriteUserRecyclerViewAdapter
    private lateinit var repoRecviewAdapter : FavoriteRepositoryRecyclerViewAdapter

    private val favViewModel : FavoriteViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }


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
            responGithubListUser.observe(viewLifecycleOwner){ respon ->
                if (respon.isNullOrEmpty()) true.isEmpty()
                peopleRecviewAdapter = FavoriteUserRecyclerViewAdapter(respon)
                binding.RecyclerVFavorite.adapter = peopleRecviewAdapter
                binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())

                peopleRecviewAdapter.apply {
                    setOnItemCallBack(object : FavoriteUserRecyclerViewAdapter.OnItemClickDetail{
                        override fun onItemClick(data: GithubListUser) {
                            findNavController().navigate(
                                BookFragmentDirections.actionAuthorFragmentToDetailFragment(data.name)
                            )
                        }
                    })
                    setOnitemDelete(object : FavoriteUserRecyclerViewAdapter.OnItemDelete{
                        override fun onItemClickDelete(data: GithubListUser) {
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
                repoRecviewAdapter = FavoriteRepositoryRecyclerViewAdapter(respon)
                binding.RecyclerVFavorite.adapter = repoRecviewAdapter
                binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())


                repoRecviewAdapter.setOnitemDelete(object : FavoriteRepositoryRecyclerViewAdapter.OnItemClickDelete{
                    override fun onItemClickDelete(data: FavoriteProject) {
                        deleteFavProject(data)
                    }
                })
            }

        }
    }

    private fun deleteFavPeople(githubListUser: GithubListUser){
        showSnackbar(githubListUser.name)
        favViewModel.deletePersonFavoritePeople(githubListUser.name)
    }

    private fun deleteFavProject(favoriteProject: FavoriteProject){
        showSnackbar(favoriteProject.name)
        favViewModel.deleteFavoriteRepo(favoriteProject)
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