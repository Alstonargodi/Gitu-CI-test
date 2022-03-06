package com.example.githubuser.view.detail.follower

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowerBinding
import com.example.githubuser.model.githubresponse.follower.FollowerResponseItem
import com.example.githubuser.viewmodel.MainViewModel


class FollowerFragment: Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : FollowerRecviewAdapter
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater)
        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading(it) }
        val name = arguments?.getString("value","")

        viewModel.getListFollowers(name!!)
        setFollowersList()

        return binding.root
    }

    private fun setFollowersList(){
        viewModel.apply {
            followerResponse.observe(viewLifecycleOwner){ responData ->
                showFollowerList(responData)
            }
        }
    }

    private fun showFollowerList(list: List<FollowerResponseItem>){
        adapter = FollowerRecviewAdapter(list)
        val recycView = binding.followerrecview
        recycView.adapter = adapter
        recycView.layoutManager = LinearLayoutManager(requireContext())

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recycView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recycView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun isLoading(isLoading:Boolean){
        binding.FollowerProgress.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}