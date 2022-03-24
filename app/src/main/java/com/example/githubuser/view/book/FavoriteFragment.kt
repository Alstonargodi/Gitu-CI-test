package com.example.githubuser.view.book

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentFavoriteBinding
import com.example.githubuser.data.local.entity.FavoritePeople
import com.example.githubuser.data.local.entity.FavoriteProject
import com.example.githubuser.view.book.adapter.FavPeopleRecviewAdapter
import com.example.githubuser.view.book.adapter.FavRepoRecviewAdapter
import com.example.githubuser.viewmodel.FavoriteViewModel
import com.example.githubuser.viewmodel.util.obtainViewModel
import com.google.android.material.snackbar.Snackbar


class FavoriteFragment : Fragment() {
    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var peopleRecviewAdapter : FavPeopleRecviewAdapter
    private lateinit var repoRecviewAdapter : FavRepoRecviewAdapter

    private lateinit var favViewModel : FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        favViewModel = obtainViewModel(requireActivity())

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
            responFavoritePeople.observe(viewLifecycleOwner){ respon ->
                if (respon.isNullOrEmpty()) true.isEmpty()
                peopleRecviewAdapter = FavPeopleRecviewAdapter(respon)
                binding.RecyclerVFavorite.adapter = peopleRecviewAdapter
                binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())

                peopleRecviewAdapter.apply {
                    setOnItemCallBack(object : FavPeopleRecviewAdapter.OnItemClickDetail{
                        override fun onItemClick(data: FavoritePeople) {
                            findNavController().navigate(
                                BookFragmentDirections.actionAuthorFragmentToDetailFragment(data.name)
                            )
                        }
                    })
                    setOnitemDelete(object : FavPeopleRecviewAdapter.OnItemDelete{
                        override fun onItemClickDelete(data: FavoritePeople) {
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
                repoRecviewAdapter = FavRepoRecviewAdapter(respon)
                binding.RecyclerVFavorite.adapter = repoRecviewAdapter
                binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())


                repoRecviewAdapter.setOnitemDelete(object : FavRepoRecviewAdapter.OnItemClickDelete{
                    override fun onItemClickDelete(data: FavoriteProject) {
                        deleteFavProject(data)
                    }
                })
            }

        }
    }

    private fun deleteFavPeople(favoritePeople: FavoritePeople){
        showSnackbar(favoritePeople.name)
        favViewModel.deletePersonFavoritePeople(favoritePeople.name)
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