package com.example.basemvvmexample.ui.view.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvmexample.R
import com.example.basemvvmexample.data.api.response.DogBreedResponse
import com.example.basemvvmexample.data.api.response.Resource
import com.example.basemvvmexample.data.api.response.Status
import com.example.basemvvmexample.databinding.BreedFragmentBinding
import com.example.basemvvmexample.ui.adapter.BreedRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.BreedViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_button
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_recycler_view
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_search
import kotlinx.android.synthetic.main.breed_fragment.progressBar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response

class BreedFragment : Fragment() {

    private val viewModel by viewModel<BreedViewModel>()
    private val sharedViewModel by sharedViewModel<SharedViewModel>()
    private lateinit var binding: BreedFragmentBinding
    private lateinit var getDogBreedsObserverResponse: Observer<Resource<Response<DogBreedResponse>>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BreedFragmentBinding.inflate(inflater, container, false).apply {
            breedViewModel = this@BreedFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            holder = this@BreedFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setUpObservers()
        breed_fragment_search.setOnClickListener {
            binding.breedViewModel?.getDogBreedsFromRepo()?.observe(viewLifecycleOwner, getDogBreedsObserverResponse)
        }
    }

    private fun initRecyclerView() {
        val recyclerViewAdapter = BreedRecyclerViewAdapter(emptyList(), sharedViewModel)
        breed_fragment_recycler_view.adapter = recyclerViewAdapter
        breed_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
        binding.breedViewModel?.dogBreedsLiveData?.observe(viewLifecycleOwner, Observer { breeds ->
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

    fun execAction() = NavHostFragment.findNavController(this).navigate(R.id.action_breedFragment_to_subBreedFragment)
}
