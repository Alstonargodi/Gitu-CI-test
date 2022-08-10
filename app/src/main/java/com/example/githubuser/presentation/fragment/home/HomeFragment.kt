package com.example.githubuser.presentation.fragment.home

import android.app.SearchManager
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.remote.utils.Resource
import com.example.core.domain.model.ListUser
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.presentation.fragment.detail.DetailFragment
import com.example.githubuser.presentation.fragment.home.adapter.UserListRecAdapter
import com.example.githubuser.presentation.utils.UtilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var adapter : UserListRecAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val viewModel : HomeViewModel by viewModels()
    private val utilViewModel by viewModels<UtilViewModel>()
    private var saveText = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

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
        viewModel.showHistoryListUser().observe(viewLifecycleOwner){
            if (it.isEmpty()){
                setDefaultList()
            }else{
                showUserList(it)
            }
        }
        viewModel.clearDetail()
        return binding.root
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
                    utilViewModel.setEmptyView(false)
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
        utilViewModel.setEmptyView(false)
        listRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            listRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            listRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        adapter.setOnItemClickDetail(object : UserListRecAdapter.OnItemClickDetail{
            override fun onItemClickDetail(name: String) {
                val detailFragment = DetailFragment()
                val bundle = Bundle()
                bundle.putString("favorite",name)
                detailFragment.arguments = bundle
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView,detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
    }

    private fun isLoading(isLoading:Boolean){
        binding.pgbarhome.visibility = if (isLoading)  View.VISIBLE  else  View.GONE
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

    override fun onDestroyView() {
        _binding = null
        Toast.makeText(requireContext(),"pn destroy view",Toast.LENGTH_SHORT).show()
        super.onDestroyView()
    }
}