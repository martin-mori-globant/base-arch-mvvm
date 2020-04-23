package com.example.basemvvmexample.di

import androidx.room.Room
import com.example.basemvvmexample.data.api.ApiHelper
import com.example.basemvvmexample.data.api.RetrofitFactory
import com.example.basemvvmexample.data.local.DogsRoomDatabase
import com.example.basemvvmexample.data.repository.MainRepository
import com.example.basemvvmexample.ui.viewmodel.BreedViewModel
import com.example.basemvvmexample.ui.viewmodel.DogGalleryViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import com.example.basemvvmexample.ui.viewmodel.SubBreedViewModel
import com.example.basemvvmexample.ui.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoriesModule = module {
    factory { Room.databaseBuilder(get(), DogsRoomDatabase::class.java, "dogs_database").build() }
    single { get<DogsRoomDatabase>().breedDao() }
    factory { ApiHelper(RetrofitFactory.getApiService()) }
    factory { MainRepository(get(), get()) }
}

val viewModelsModule = module {
    viewModel { BreedViewModel(get()) }
    viewModel { SubBreedViewModel() }
    viewModel { DogGalleryViewModel() }
    viewModel { SharedViewModel() }
    viewModel { MainViewModel(get()) }
}
