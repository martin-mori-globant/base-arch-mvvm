package com.example.basemvvmexample.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvmexample.R
import com.example.basemvvmexample.data.api.ApiHelper
import com.example.basemvvmexample.data.api.RetrofitFactory
import com.example.basemvvmexample.data.api.response.DogBreedResponse
import com.example.basemvvmexample.data.api.response.Resource
import com.example.basemvvmexample.data.api.response.Status
import com.example.basemvvmexample.data.local.DogsRoomDatabase
import com.example.basemvvmexample.databinding.BreedFragmentBinding
import com.example.basemvvmexample.ui.adapter.BreedRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.BreedViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_button
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_recycler_view
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_search
import kotlinx.android.synthetic.main.breed_fragment.progressBar
import retrofit2.Response

class BreedFragmentLUCHO : Fragment() {

    // private lateinit var viewModel: BreedViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var recyclerViewAdapter: BreedRecyclerViewAdapter

    private lateinit var breedFragmentBinding: BreedFragmentBinding
    private lateinit var getDogBreedsObserverResponse: Observer<Resource<Response<DogBreedResponse>>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val factory = BreedViewModel.Factory(ApiHelper(RetrofitFactory.getApiService()), DogsRoomDatabase.getDatabase(activity!!.applicationContext).breedDao())
        breedFragmentBinding = BreedFragmentBinding.inflate(inflater, container, false).apply {
            breedViewModel = ViewModelProvider(this@BreedFragmentLUCHO, factory).get(BreedViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }

        return breedFragmentBinding.root
        // return inflater.inflate(R.layout.breed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        // initializeUI()

        breed_fragment_button.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_breedFragment_to_subBreedFragment)
        }

        breed_fragment_search.setOnClickListener {
            breedFragmentBinding.breedViewModel?.getDogBreedsFromRepo()?.observe(viewLifecycleOwner, getDogBreedsObserverResponse)
        }

        /*
        breed_fragment_load.setOnClickListener {
            breedFragmentBinding.breedViewModel?.loadOnDataBase()?.observe(viewLifecycleOwner, getDogBreedsObserverResponse)
        }

         */
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = BreedRecyclerViewAdapter(emptyList(), sharedViewModel)
        breed_fragment_recycler_view.adapter = recyclerViewAdapter
        breed_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
        breedFragmentBinding.breedViewModel?.dogBreedsLiveData?.observe(viewLifecycleOwner, Observer { breeds ->
            breeds?.let { recyclerViewAdapter.setWords(breeds) }
        })
    }

    private fun setUpObservers() {
        getDogBreedsObserverResponse = Observer<Resource<Response<DogBreedResponse>>> {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        hideLoading()
                    }
                    Status.ERROR -> {
                        hideLoading()
                    }
                    Status.LOADING -> {
                        showLoading()
                    }
                }
            }
        }
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        breed_fragment_recycler_view.visibility = View.INVISIBLE
        breed_fragment_button.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
        breed_fragment_recycler_view.visibility = View.VISIBLE
        breed_fragment_button.visibility = View.VISIBLE
    }
}
