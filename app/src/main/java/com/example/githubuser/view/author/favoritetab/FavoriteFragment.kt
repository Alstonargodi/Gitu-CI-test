package com.example.githubuser.view.author.favoritetab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentFavoriteBinding
import com.example.githubuser.local.entity.FavoritePeople
import com.example.githubuser.view.author.adapter.FavPeopleRecviewAdapter
import com.example.githubuser.viewmodel.FavoriteViewModel
import com.example.githubuser.viewmodel.util.obtainViewModel


class FavoriteFragment : Fragment() {
    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var peopleRecviewAdapter : FavPeopleRecviewAdapter
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
        val index = arguments?.getInt("number")
        when(index){
            1 -> binding.tvPage.text = "index satu"
            2 -> showFavPeople()
        }
    }

    private fun showFavPeople(){
        favViewModel.readFavoritePeople().observe(viewLifecycleOwner){ respon ->
            peopleRecviewAdapter = FavPeopleRecviewAdapter(respon)
            binding.RecyclerVFavorite.adapter = peopleRecviewAdapter
            binding.RecyclerVFavorite.layoutManager = LinearLayoutManager(requireContext())

            peopleRecviewAdapter.setOnItemCallBack(object : FavPeopleRecviewAdapter.OnItemCallBack{
                override fun onItemClick(data: FavoritePeople) {
                    testshow(data)
                }
            })
        }
    }

    private fun testshow(favoritePeople: FavoritePeople){
        Log.d("test",favoritePeople.name)
    }

}