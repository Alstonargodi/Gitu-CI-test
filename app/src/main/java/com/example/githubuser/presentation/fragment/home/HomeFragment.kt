package com.example.githubuser.presentation.fragment.home

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.ListUser
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.presentation.fragment.home.adapter.UserListRecAdapter
import com.example.githubuser.presentation.utils.UtilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var adapter : UserListRecAdapter
    private lateinit var binding: FragmentHomeBinding

    private val viewModel : HomeViewModel by viewModels()
    private val utilViewModel by viewModels<UtilViewModel>()

    private var saveText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar2)
        binding.toolbar2.inflateMenu(R.menu.barmenu)
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner){ isLoading(it) }
        }
        utilViewModel.apply {
            textQuery.observe(viewLifecycleOwner){ text ->
                saveText = text
            }
        }
        setDefaultList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showErrorText()
        setDefaultList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.barmenu, menu)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        val authorView = menu.findItem(R.id.author)

        authorView.apply {
            setOnMenuItemClickListener {
                try {
                    val bookFragment =
                        Class.forName("com.example.favorite.BookFragment").newInstance() as Fragment
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView,bookFragment)
                        .addToBackStack(null)
                        .commit()
                }catch (e : Exception){
                    Log.d("HomeFragment",e.toString())
                }
                true
            }
        }

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            queryHint = "input username"

            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    setSearchResult(query?:"",false)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun setSearchResult(search : String,default : Boolean){
        if(!default){
            viewModel.deleteListUser()
            utilViewModel.setEmptys(false)
        }
        viewModel.getListUser(search).observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->{
                    binding.pgbarhome.visibility = View.VISIBLE
                }
                is Resource.Success ->{
                    it.data?.let {
                      it1 -> showUserList(it1)
                    }
                }
                is Resource.Error ->{
                    Log.d("remoteRepository home", it.message.toString())
                    binding.pgbarhome.visibility = View.GONE
                }
                else -> {}
            }
        }
        binding.tvEmptyview.visibility = View.GONE
        utilViewModel.saveText(search)
    }

    private fun showUserList(list: List<ListUser>){
        binding.pgbarhome.visibility = View.GONE
        adapter = UserListRecAdapter(list.distinct())
        val listRecyclerView = binding.RecviewUser
        listRecyclerView.adapter = adapter
        utilViewModel.setEmptys(false)
        listRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            listRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            listRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showErrorText(){
        binding.apply {
            viewModel.errorResponse.observe(viewLifecycleOwner){ errorResponse ->
                if (errorResponse.isNotEmpty()){
                        tvEmptyview.visibility = View.GONE
                        RecviewUser.visibility = View.GONE
                        includeerror.layoutError.visibility = View.VISIBLE
                        includeerror.TvEmptyconnect.text = errorResponse.toString()
                        includeerror.BtnTryEmptyconnect.setOnClickListener {
                            viewModel.getListUser(saveText)
                            "search result for $saveText".also { includeerror.TvEmptyconnect.text = it }

                            includeerror.layoutError.visibility = View.GONE
                            tvEmptyview.visibility = View.GONE
                            RecviewUser.visibility = View.VISIBLE
                            includeerror.layoutError.visibility = View.GONE
                        }
                    }
                }
            }
    }

    private fun showEmptyView(isEmpty : Boolean){
        binding.includeempty.apply {
            if (isEmpty){
                layoutemptyview.visibility = View.VISIBLE
                "No data for $saveText".also { tvResultempty.text = it }
            }else{
                layoutemptyview.visibility = View.GONE
                "No data for $saveText".also { tvResultempty.text = it }
            }
        }
    }

    private fun isLoading(isLoading:Boolean){
        binding.Homeprogress.visibility = if (isLoading)  View.VISIBLE  else  View.GONE
    }

    private fun setDefaultList(){
        utilViewModel.isEmpty.observe(viewLifecycleOwner){ isUserNotExist ->
            if (isUserNotExist == true){
                setSearchResult(default_user,true)
                binding.tvEmptyview.visibility = View.GONE
            }else if (isUserNotExist == false){
                binding.tvEmptyview.visibility = View.GONE
            }
        }
    }

    companion object{
        private const val default_user = "followers:>1000"
    }
}