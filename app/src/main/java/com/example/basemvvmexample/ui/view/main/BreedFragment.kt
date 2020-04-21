package com.example.basemvvmexample.ui.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvmexample.R
import com.example.basemvvmexample.ui.BreedRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.BreedViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_button
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_recycler_view

class BreedFragment : Fragment() {

    companion object {
        fun newInstance() = BreedFragment()
    }

    private lateinit var viewModel: BreedViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.breed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        breed_fragment_button.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_breedFragment_to_subBreedFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BreedViewModel::class.java)
        if (activity != null) {
            sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewAdapter = BreedRecyclerViewAdapter(listOf("dog_1", "dog_2", "dog_3"), sharedViewModel)
        breed_fragment_recycler_view.adapter = recyclerViewAdapter
        breed_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
    }
}
