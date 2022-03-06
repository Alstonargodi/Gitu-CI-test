package com.example.githubuser.view.detail.following

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowingBinding
import com.example.githubuser.model.githubresponse.following.FollowingResponseItem
import com.example.githubuser.viewmodel.FollowingViewModel
import com.example.githubuser.viewmodel.UtilViewModel


class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : FollowingReviewAdapter
    private val followingViewModel by viewModels<FollowingViewModel>()
    private val utilViewModel by viewModels<UtilViewModel>()


    private var userName = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)
        userName = arguments?.getString("value","").toString()


        followingViewModel.apply {
            isLoading.observe(viewLifecycleOwner){ isLoading(it) }
            getListFollowing(userName)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFollowingList()
    }

    private fun setFollowingList(){
        followingViewModel.apply {
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
        utilViewModel.setEmptys(adapter.itemCount)
        emptyChecker()

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recview.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recview.layoutManager = LinearLayoutManager(requireContext())
        }
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

    private fun emptyChecker(){
        binding.apply {
            utilViewModel.isEmpty.observe(viewLifecycleOwner){ value ->
                if (value == 0){
                    emptyStatmentFollowing.visibility = View.VISIBLE
                    emptyStatmentFollowing.text = "$userName never follow someone"
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}