package com.example.githubuser.view.detail.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowingBinding
import com.example.githubuser.view.detail.adapter.FollowingRecviewAdapter
import com.example.githubuser.viewmodel.MainViewModel


class FollowingFragment(var name : String) : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : FollowingRecviewAdapter
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)

        adapter = FollowingRecviewAdapter()
        val recview = binding.followingRecview
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())

        setFollowingList(name)
        return binding.root
    }

    private fun setFollowingList(name : String){
        viewModel.apply {
            getListFollowing(name)
            followingResponse.observe(viewLifecycleOwner){
                adapter.setData(it)
            }
        }
    }

}