package com.example.githubuser.view.detail.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentRepoBinding
import com.example.githubuser.model.githubresponse.repository.RepoResponseItem
import com.example.githubuser.view.detail.adapter.ReposRecviewAdapter
import com.example.githubuser.viewmodel.MainViewModel


class RepositoryFragment(var name : String) : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : ReposRecviewAdapter
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(layoutInflater)
        setUserRepo(name)
        return binding.root
    }

    private fun setUserRepo(name : String){
        viewModel.apply {
            getUserRepo(name)
            repoResponse.observe(viewLifecycleOwner){ responData ->
                showRepositoryList(responData)
            }
        }
    }

    private fun showRepositoryList(list: List<RepoResponseItem>){
        adapter = ReposRecviewAdapter(list)
        val recview = binding.Reporecview
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}