package com.example.githubuser.view.detail.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowingBinding
import com.example.githubuser.model.githubresponse.following.FollowingResponseItem
import com.example.githubuser.view.detail.adapter.FollowingReviewAdapter
import com.example.githubuser.viewmodel.MainViewModel


class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : FollowingReviewAdapter
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)
        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading(it) }
        val name = arguments?.getString("value","")
        setFollowingList(name!!)
        return binding.root
    }

    private fun setFollowingList(name : String){
        viewModel.apply {
            getListFollowing(name)
            followingResponse.observe(viewLifecycleOwner){ responData->
                showFollowingList(responData)
            }
        }
    }

    private fun showFollowingList(list: List<FollowingResponseItem>){
        adapter = FollowingReviewAdapter(list)
        val recview = binding.followingRecview
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun isLoading(isLoading:Boolean){
        binding.Followingprogress.apply {
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