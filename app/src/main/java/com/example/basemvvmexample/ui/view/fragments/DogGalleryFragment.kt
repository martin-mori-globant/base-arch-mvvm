package com.example.basemvvmexample.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvmexample.R
import com.example.basemvvmexample.ui.adapters.DogGalleryRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.DogGalleryViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.dog_gallery_fragment.dog_gallery_fragment_breed
import kotlinx.android.synthetic.main.dog_gallery_fragment.dog_gallery_fragment_button
import kotlinx.android.synthetic.main.dog_gallery_fragment.dog_gallery_fragment_recycler_view
import kotlinx.android.synthetic.main.dog_gallery_fragment.dog_gallery_fragment_sub_breed

class DogGalleryFragment : Fragment() {

    companion object {
        fun newInstance() = DogGalleryFragment()
    }

    private lateinit var viewModel: DogGalleryViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dog_gallery_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dog_gallery_fragment_button.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_dogGalleryFragment_to_breedFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DogGalleryViewModel::class.java)
        if (activity != null) {
            sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        }
        dog_gallery_fragment_breed.text = sharedViewModel.breed.value
        dog_gallery_fragment_sub_breed.text = sharedViewModel.subBreed.value
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewAdapter = DogGalleryRecyclerViewAdapter(listOf("img_dog_1", "img_dog_2", "img_dog_3"), sharedViewModel)
        dog_gallery_fragment_recycler_view.adapter = recyclerViewAdapter
        dog_gallery_fragment_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}
