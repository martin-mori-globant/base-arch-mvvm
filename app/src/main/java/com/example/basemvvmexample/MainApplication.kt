package com.example.basemvvmexample

import android.app.Application
import com.example.basemvvmexample.di.repositoriesModule
import com.example.basemvvmexample.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(viewModelsModule, repositoriesModule))
        }
    }
}
