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
import com.example.core.data.remote.apiresponse.follower.FollowerUserResponseItem
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.UserFollower
import com.example.githubuser.presentation.fragment.detail.DetailFragmentDirections
import com.example.githubuser.presentation.fragment.follower.adapter.FollowerRecyclerViewAdapter
import com.example.githubuser.presentation.utils.UtilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment: Fragment() {

    private lateinit var binding: FragmentFollowerBinding
    private lateinit var adapter : FollowerRecyclerViewAdapter

    private val followerViewModel : FollowerViewModel by viewModels()

    private val utilViewModel by viewModels<UtilViewModel>()
    private var userName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(layoutInflater)
        userName = arguments?.getString("value","").toString()

        utilViewModel.apply {
            textQuery.observe(viewLifecycleOwner){
                userName = it
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFollowersList()
        userChecker()
    }

    private fun setFollowersList(){
        followerViewModel.getListFollowers(userName).observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Loading->{

                }
                is Resource.Success->{
                    response.data?.let { showFollowerList(it) }
                }
                is Resource.Error->{

                }
            }
        }
    }

    private fun userChecker(){
        utilViewModel.isEmpty.observe(viewLifecycleOwner) { isDataNotExist ->
            if (isDataNotExist == true) {
                setErrorView()
            }
        }
    }

    private fun showFollowerList(list: List<UserFollower>){
        adapter = FollowerRecyclerViewAdapter(list)
        val recyclerView = binding.followerrecview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        utilViewModel.apply { if (adapter.itemCount== 0) setEmptyView(true) else setEmptyView(false) }
        emptyChecker()

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

    private fun isLoading(isLoading:Boolean){
        binding.FollowerProgress.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun clearData(){
        followerViewModel.apply {
            deleteUserFollower()
            deleteUserRepository()
        }
    }

    private fun emptyChecker(){
        binding.apply {
            utilViewModel.isEmpty.observe(viewLifecycleOwner){ isDataNotExist ->
                if (isDataNotExist == true){
                    EmptyStatment.visibility = View.VISIBLE
                    "$userName doesn't have any followers yet".also { EmptyStatment.text = it }
                }
            }
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
}