package com.example.basemvvmexample.ui.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basemvvmexample.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
