package com.example.githubuser.view.detail.tab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowerBinding
import com.example.githubuser.view.detail.adapter.FollowerRecviewAdapter
import com.example.githubuser.viewmodel.MainViewModel


class FollowerFragment(var name : String) : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : FollowerRecviewAdapter
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater)

        adapter = FollowerRecviewAdapter()
        val recview = binding.followerrecview
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())

        setFollowersList(name)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val text = arguments?.getString("name")

        Log.d("argsfollower",text.toString())
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setFollowersList(name : String){
        viewModel.apply {
            getListFollowers(name)
            followerResponse.observe(viewLifecycleOwner){ respon ->
                adapter.setData(respon)
            }
        }

    }
}