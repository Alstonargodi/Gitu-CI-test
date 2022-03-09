package com.example.githubuser.view.home


import android.app.SearchManager
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.remote.githubresponse.ItemsItem
import com.example.githubuser.view.home.adapter.UserListRecAdapter
import com.example.githubuser.viewmodel.MainViewModel
import com.example.githubuser.viewmodel.UtilViewModel


class HomeFragment : Fragment() {
    private lateinit var adapter : UserListRecAdapter
    private lateinit var binding: FragmentHomeBinding

    private val mainViewModel by viewModels<MainViewModel>()
    private val utilViewModel by viewModels<UtilViewModel>()

    private var saveText = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)


        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.maincolor)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar2)
        binding.toolbar2.inflateMenu(R.menu.barmenu)


        mainViewModel.apply {
            isLoading.observe(viewLifecycleOwner){ isLoading(it) }
            listresponse.observe(viewLifecycleOwner){ showUserList(it) }
        }

        utilViewModel.apply {
            textQuery.observe(viewLifecycleOwner){ text ->
                "search result for $text".also { binding.CurrsearchTv.text = it }
                saveText = text
            }
        }
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
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAuthorFragment())
                true
            }
        }


        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            queryHint = "input username"

            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    setSearchResult(query?:"")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

        }
    }

    private fun setSearchResult(search : String){
        mainViewModel.apply {
            getListUser(search)
            binding.tvEmptyview.visibility = View.GONE
        }
        utilViewModel.saveText(search)
        "search result for $search".also { binding.CurrsearchTv.text = it }

        when(search){
            default_user->binding.CurrsearchTv.text = getString(R.string.Topuser)
        }
    }



    private fun showUserList(list: List<ItemsItem>){
        adapter = UserListRecAdapter(list)
        val recView = binding.RecviewUser
        recView.adapter = adapter
        utilViewModel.setEmptys(false)

        if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recView.layoutManager = GridLayoutManager(requireContext(),2)
        }else{
            recView.layoutManager = LinearLayoutManager(requireContext())
        }

    }


    private fun showErrorText(){
        binding.apply {
            mainViewModel.errorResponse.observe(viewLifecycleOwner){ errorResponse ->
                if (errorResponse.isNotEmpty()){
                        ErrortextTv.visibility = View.VISIBLE
                        TryagainHome.visibility = View.VISIBLE
                        tvEmptyview.visibility = View.GONE
                        RecviewUser.visibility = View.GONE
                        ErrortextTv.text = errorResponse.toString()
                        TryagainHome.setOnClickListener {
                             mainViewModel.getListUser(saveText)
                            "search result for $saveText".also { binding.CurrsearchTv.text = it }

                            ErrortextTv.visibility = View.GONE
                            TryagainHome.visibility = View.GONE
                            tvEmptyview.visibility = View.GONE
                            RecviewUser.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }


    private fun isLoading(isLoading:Boolean){
        binding.Homeprogress.visibility = if (isLoading)  View.VISIBLE  else  View.GONE
    }

    private fun setDefaultList(){
        utilViewModel.isEmpty.observe(viewLifecycleOwner){ isUserNotExist ->
            if (isUserNotExist == true){
                setSearchResult(default_user) // Top User when search empty
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