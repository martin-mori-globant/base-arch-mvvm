package com.example.basemvvmexample.ui.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basemvvmexample.databinding.MainActivityBinding
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val sharedViewModel by viewModel<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
