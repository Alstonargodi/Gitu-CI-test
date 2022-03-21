package com.example.githubuser.view.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFavoriteBinding
import com.example.githubuser.local.entity.FavoritePeople
import com.example.githubuser.local.entity.FavoriteProject
import com.example.githubuser.view.book.adapter.FavPeopleRecviewAdapter
import com.example.githubuser.view.book.adapter.FavRepoRecviewAdapter
import com.example.githubuser.viewmodel.FavoriteViewModel
import com.example.githubuser.viewmodel.util.obtainViewModel


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
        favViewModel.readFavoritePeople().observe(viewLifecycleOwner){ respon ->
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
                        deleteFav(data)
                    }
                })
            }
        }
    }

    private fun showFavRepository(){
        favViewModel.readFavoriteProject().observe(viewLifecycleOwner){respon ->
            repoRecviewAdapter = FavRepoRecviewAdapter(respon)
            binding.RecyclerVFavorite.adapter = repoRecviewAdapter
            binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())

            repoRecviewAdapter.setOnitemDelete(object : FavRepoRecviewAdapter.OnItemClickDelete{
                override fun onItemClickDelete(data: FavoriteProject) {
                    favViewModel.deleteFavoriteRepo(data)
                }
            })
        }
    }

    private fun deleteFav(favoritePeople: FavoritePeople){
        favViewModel.deleteFavoritePeople(favoritePeople)
    }

}