package com.example.githubuser.presentation.fragment.following

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.UserFollowing
import com.example.githubuser.databinding.FragmentFollowingBinding
import com.example.githubuser.presentation.fragment.detail.DetailFragmentDirections
import com.example.githubuser.presentation.fragment.following.adapter.FollowingRecyclerViewAdapter
import com.example.githubuser.presentation.utils.UtilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : FollowingRecyclerViewAdapter

    private val followingViewModel :FollowingViewModel by viewModels()
    private val utilViewModel by viewModels<UtilViewModel>()
    private var userName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)
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
        setFollowingList()
        userChecker()
    }

    private fun setFollowingList(){
        followingViewModel.getListFollowing(userName).observe(viewLifecycleOwner){response->
            when(response){
                is Resource.Loading->{

                }
                is Resource.Success->{
                    response.data?.let { showFollowingList(it) }
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

    private fun showFollowingList(list: List<UserFollowing>){
        adapter = FollowingRecyclerViewAdapter(list)
        val recyclerView = binding.followingRecview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        utilViewModel.apply {
            if (adapter.itemCount== 0) setEmptyView(true) else setEmptyView(false)
        }
        emptyChecker()

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        adapter.onItemCLickDetail(object : FollowingRecyclerViewAdapter.OnItemClickDetil{
            override fun onItemClickDetail(userName: String) {
                clearData()
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentSelf(userName))
            }
        })
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
            utilViewModel.isEmpty.observe(viewLifecycleOwner){ isUserNotExist ->
                if (isUserNotExist == true){
                    emptyStatmentFollowing.visibility = View.VISIBLE
                    "$userName never follow someone".also { emptyStatmentFollowing.text = it }
                }
            }
        }
    }

    private fun clearData(){
        followingViewModel.apply {
            deleteUserFollowing()
            deleteUserRepository()
        }
    }

    private fun setErrorView(){
        binding.apply {
            followingViewModel.errorResponse.observe(viewLifecycleOwner){ response ->
                Log.d("error response",response)
                if (response.isNotEmpty()){
                    emptyStatmentFollowing.visibility = View.VISIBLE
                    ("$response\n \n Try Again").also { emptyStatmentFollowing.text = it }
                    emptyStatmentFollowing.setOnClickListener {
                        followingViewModel.getListFollowing(userName)
                        emptyStatmentFollowing.visibility = View.GONE
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}