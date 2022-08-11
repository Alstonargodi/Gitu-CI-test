package com.example.githubuser.presentation.fragment.follower

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowerBinding
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.UserFollower
import com.example.githubuser.databinding.FragmentRepoBinding
import com.example.githubuser.presentation.fragment.detail.DetailFragmentDirections
import com.example.githubuser.presentation.fragment.follower.adapter.FollowerRecyclerViewAdapter
import com.example.githubuser.presentation.utils.UtilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment: Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter : FollowerRecyclerViewAdapter

    private val followerViewModel : FollowerViewModel by viewModels()

    private val utilViewModel by viewModels<UtilViewModel>()
    private var userName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater)
        userName = arguments?.getString("value","").toString()

        utilViewModel.apply {
            textQuery.observe(viewLifecycleOwner){
                userName = it
            }
        }
        showUserFollowers()
        return binding.root
    }



    private fun showUserFollowers(){
        followerViewModel.getListFollowers(userName).observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Loading->{
                    binding.FollowerProgress.visibility = View.VISIBLE
                }
                is Resource.Success->{
                    response.data?.let { showFollowerList(it) }
                    binding.FollowerProgress.visibility = View.GONE
                }
                is Resource.Error->{
                    binding.FollowerProgress.visibility = View.GONE
                    setErrorView()
                }
            }
        }
    }



    private fun showFollowerList(list: List<UserFollower>){
        adapter = FollowerRecyclerViewAdapter(list)
        val recyclerView = binding.followerrecview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        utilViewModel.apply { if (adapter.itemCount== 0) setEmptyView(true) else setEmptyView(false) }

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        adapter.onItemClickDetail(object : FollowerRecyclerViewAdapter.OnItemCallDetail{
            override fun onItemCallDetail(username: String) {
                clearData()
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentSelf(username))
            }
        })

    }

    private fun clearData(){
        followerViewModel.apply {
            deleteUserFollower()
            deleteUserRepository()
        }
    }


    private fun setErrorView(){
        binding.apply {
            followerViewModel.errorResponse.observe(viewLifecycleOwner){ response ->
                if (response.isNotEmpty()){
                    EmptyStatment.visibility = View.VISIBLE
                    ("$response\n \n Try Again").also { EmptyStatment.text = it }
                    EmptyStatment.setOnClickListener {
                        followerViewModel.getListFollowers(userName)
                        EmptyStatment.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}