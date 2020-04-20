package com.example.basemvvmexample.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.basemvvmexample.data.api.ApiHelper
import com.example.basemvvmexample.data.api.RetrofitFactory
import com.example.basemvvmexample.data.model.DogImage
import com.example.basemvvmexample.data.model.Resource
import com.example.basemvvmexample.data.model.Status
import com.example.basemvvmexample.databinding.MainFragmentBinding
import com.example.basemvvmexample.ui.viewmodel.MainViewModel
import retrofit2.Response

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var getDogImageObserver: Observer<Resource<Response<DogImage>>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val factory = MainViewModel.Factory(ApiHelper(RetrofitFactory.getApiService()))
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false).apply {
            mainViewModel = ViewModelProvider(this@MainFragment, factory).get(MainViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }

        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        initializeUI()

        mainFragmentBinding.btnNext.setOnClickListener {
            mainFragmentBinding.mainViewModel?.getDogImage()
                    ?.observe(viewLifecycleOwner, getDogImageObserver)
        }
    }

    private fun initializeUI() {
        mainFragmentBinding.mainViewModel?.getDogImage()
                ?.observe(viewLifecycleOwner, getDogImageObserver)
    }

    private fun setUpObservers() {
        getDogImageObserver = Observer<Resource<Response<DogImage>>> {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        //Do something if success
                    }
                    Status.ERROR -> {
                        //Do something if error
                    }
                    Status.LOADING -> {
                        //Do something if loading
                    }
                }
            }
        }
    }
}
