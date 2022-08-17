package com.example.favorite

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.local.entity.favorite.favoriteproject.FavoriteProject
import com.example.core.data.local.entity.favorite.favoriteuser.FavoriteUser
import com.example.core.di.FavoriteModuleDependecies
import com.example.favorite.adapter.FavoriteUserRecyclerViewAdapter
import com.example.favorite.adapter.FavoriteRepositoryRecyclerViewAdapter
import com.example.favorite.component.DaggerFavoriteComponent
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.viewmodel.FavoriteViewModel
import com.example.favorite.viewmodel.ViewModelFactory
import com.example.githubuser.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var peopleRecyclerViewAdapter : FavoriteUserRecyclerViewAdapter
    private lateinit var repositoryRecyclerViewAdapter : FavoriteRepositoryRecyclerViewAdapter

    private val favoriteViewModel : FavoriteViewModel by viewModels{
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDepedencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependecies::class.java
                )
            )
            .build()
            .inject(this)
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        favoriteViewModel.isLoading.observe(viewLifecycleOwner){ respon ->
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
        favoriteViewModel.clearDetail()
        favoriteViewModel.apply {
            readFavoritePeople()
            responGithubListUser.observe(viewLifecycleOwner){ respon ->
                peopleRecyclerViewAdapter = FavoriteUserRecyclerViewAdapter(respon)
                binding.RecyclerVFavorite.adapter = peopleRecyclerViewAdapter
                binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())

                peopleRecyclerViewAdapter.apply {
                    setOnItemCallBack(object : FavoriteUserRecyclerViewAdapter.OnItemClickDetail{
                        override fun onItemClick(data: FavoriteUser) {
                            val detailFragment = Class
                                    .forName("com.example.githubuser.presentation.fragment.detail.DetailFragment")
                                    .newInstance() as Fragment

                            val bundle = Bundle()
                            bundle.putString("favorite",data.username)
                            detailFragment.arguments = bundle
                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragmentContainerView,detailFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    })
                    setOnItemDeleted(object : FavoriteUserRecyclerViewAdapter.OnItemDelete{
                        override fun onItemClickDelete(data: FavoriteUser) {
                            deleteFavPeople(data)
                        }
                    })
                }
            }
        }
    }

    private fun showFavRepository(){
        favoriteViewModel.apply {
            readFavoriteProject()
            responFavoriteRepo.observe(viewLifecycleOwner){ respon ->
                repositoryRecyclerViewAdapter = FavoriteRepositoryRecyclerViewAdapter(respon)
                binding.RecyclerVFavorite.adapter = repositoryRecyclerViewAdapter
                binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())

                repositoryRecyclerViewAdapter.setOnItemDeleted(object : FavoriteRepositoryRecyclerViewAdapter.OnItemClickDelete{
                    override fun onItemClickDelete(data: FavoriteProject) {
                        deleteFavProject(data)
                    }
                })
            }

        }
    }

    private fun deleteFavPeople(githubListUser: FavoriteUser){
        githubListUser.username?.let {
            showSnackbar(it)
            favoriteViewModel.deletePersonFavoritePeople(it)
        }
    }

    private fun deleteFavProject(favoriteProject: FavoriteProject){
        showSnackbar(favoriteProject.name)
        favoriteViewModel.deleteFavoriteRepo(favoriteProject)
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
}