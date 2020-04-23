package com.example.basemvvmexample.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.basemvvmexample.data.api.response.DogImageResponse
import com.example.basemvvmexample.data.api.response.Resource
import com.example.basemvvmexample.data.api.response.Status
import com.example.basemvvmexample.databinding.MainFragmentBinding
import com.example.basemvvmexample.ui.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response

class MainFragment : Fragment() {

    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var getDogImageObserver: Observer<Resource<Response<DogImageResponse>>>
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false).apply {
            mainViewModel = this@MainFragment.mainViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        initializeUI()

        mainFragmentBinding.btnNext.setOnClickListener {
            mainFragmentBinding.mainViewModel?.getDogImage()?.observe(viewLifecycleOwner, getDogImageObserver)
        }
    }

    private fun initializeUI() {
        mainFragmentBinding.mainViewModel?.getDogImage()?.observe(viewLifecycleOwner, getDogImageObserver)
    }

    private fun setUpObservers() {
        getDogImageObserver = Observer {
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
    }
}
