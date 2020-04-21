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
import com.example.basemvvmexample.ui.SubBreedRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import com.example.basemvvmexample.ui.viewmodel.SubBreedViewModel
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_recycler_view
import kotlinx.android.synthetic.main.sub_breed_fragment.sub_breed_fragment_button
import kotlinx.android.synthetic.main.sub_breed_fragment.sub_breed_fragment_text

class SubBreedFragment : Fragment() {

    companion object {
        fun newInstance() = SubBreedFragment()
    }

    private lateinit var viewModel: SubBreedViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sub_breed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sub_breed_fragment_button.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_subBreedFragment_to_dogGalleryFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SubBreedViewModel::class.java)
        if (activity != null) {
            sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        }
        sub_breed_fragment_text.text = sharedViewModel.breed.value
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewAdapter = SubBreedRecyclerViewAdapter(listOf("sub_dog_1", "sub_dog_2", "sub_dog_3"), sharedViewModel)
        breed_fragment_recycler_view.adapter = recyclerViewAdapter
        breed_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
    }
}
