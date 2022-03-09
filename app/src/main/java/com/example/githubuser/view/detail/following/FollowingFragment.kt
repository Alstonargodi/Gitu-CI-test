package com.example.githubuser.view.detail.following

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowingBinding
import com.example.githubuser.remote.githubresponse.following.FollowingResponseItem
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
        followingViewModel.apply {
            getListFollowing(userName)
            followingResponse.observe(viewLifecycleOwner){ responData->
                isLoading.observe(viewLifecycleOwner){ isLoading(it) }
                showFollowingList(responData)
                utilViewModel.apply {
                    saveText(userName)
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

    private fun showFollowingList(list: List<FollowingResponseItem>){
        adapter = FollowingReviewAdapter(list)
        val recview = binding.followingRecview
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())
        utilViewModel.apply {
            if (adapter.itemCount== 0) setEmptys(true) else setEmptys(false)
        }
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
            utilViewModel.isEmpty.observe(viewLifecycleOwner){ isUserNotExist ->
                if (isUserNotExist == true){
                    emptyStatmentFollowing.visibility = View.VISIBLE
                    "$userName never follow someone".also { emptyStatmentFollowing.text = it }
                }
            }
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