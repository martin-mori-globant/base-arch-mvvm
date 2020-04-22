package com.example.basemvvmexample.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.basemvvmexample.data.api.response.DogBreedResponse
import com.example.basemvvmexample.data.api.response.DogImageResponse
import com.example.basemvvmexample.data.api.response.Resource
import com.example.basemvvmexample.data.api.response.Status
import com.example.basemvvmexample.databinding.MainFragmentBinding
import com.example.basemvvmexample.ui.viewmodel.main.MainViewModel
import retrofit2.Response

class MainFragment : Fragment() {

    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var getDogImageResponseObserver: Observer<Resource<Response<DogImageResponse>>>
    private lateinit var getDogBreedsObserverResponse: Observer<Resource<Response<DogBreedResponse>>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false).apply {
            mainViewModel = ViewModelProvider(this@MainFragment).get(MainViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }

        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        initializeUI()

        mainFragmentBinding.btnNext.setOnClickListener {
            mainFragmentBinding.mainViewModel?.getDogImage()?.observe(viewLifecycleOwner, getDogImageResponseObserver)
        }

        mainFragmentBinding.btnBreeds.setOnClickListener {
            mainFragmentBinding.mainViewModel?.getDogBreeds()
                    ?.observe(viewLifecycleOwner, getDogBreedsObserverResponse)
        }
    }

    private fun initializeUI() {
        mainFragmentBinding.mainViewModel?.getDogImage()?.observe(viewLifecycleOwner, getDogImageResponseObserver)
    }

    private fun setUpObservers() {
        getDogImageResponseObserver = Observer<Resource<Response<DogImageResponse>>> {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        // Do something if success
                    }
                    Status.ERROR -> {
                        // Do something if error
                    }
                    Status.LOADING -> {
                        // Do something if loading
                    }
                }
            }
        }

        getDogBreedsObserverResponse = Observer<Resource<Response<DogBreedResponse>>> {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val list = resource.data?.body()?.message?.keys?.toList()
                        mainFragmentBinding.mainViewModel?.saveThatShit(list)
                    }
                    Status.ERROR -> {
                        // Do something if error
                    }
                    Status.LOADING -> {
                        // Do something if loading
                    }
                }
            }
        }
    }
}
