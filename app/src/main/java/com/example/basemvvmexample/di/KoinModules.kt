package com.example.basemvvmexample.di

import com.example.basemvvmexample.data.api.ApiHelper
import com.example.basemvvmexample.data.api.RetrofitFactory
import com.example.basemvvmexample.data.repository.MainRepository
import com.example.basemvvmexample.ui.viewmodel.BreedViewModel
import com.example.basemvvmexample.ui.viewmodel.DogGalleryViewModel
import com.example.basemvvmexample.ui.viewmodel.MainViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import com.example.basemvvmexample.ui.viewmodel.SubBreedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoriesModule = module {
    factory { ApiHelper(RetrofitFactory.getApiService()) }
    factory { MainRepository(get()) }
}

val viewModelsModule = module {
    viewModel { BreedViewModel() }
    viewModel { SubBreedViewModel() }
    viewModel { DogGalleryViewModel() }
    viewModel { SharedViewModel() }
    viewModel { MainViewModel(get()) }
}
