package com.example.basemvvmexample.ui.view.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvmexample.R
import com.example.basemvvmexample.databinding.BreedFragmentBinding
import com.example.basemvvmexample.ui.adapter.BreedRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.BreedViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_recycler_view

class BreedFragment : Fragment() {

    private lateinit var viewModel: BreedViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: BreedFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.breed_fragment, container, false)
        binding.handler = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BreedViewModel::class.java)
        sharedViewModel = activity?.let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }!!
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewAdapter = BreedRecyclerViewAdapter(
                listOf(getString(R.string.dog_1), getString(R.string.dog_2), getString(R.string.dog_3)),
                sharedViewModel)
        breed_fragment_recycler_view.adapter = recyclerViewAdapter
        breed_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    fun execAction() = NavHostFragment.findNavController(this).navigate(R.id.action_breedFragment_to_subBreedFragment)
}
