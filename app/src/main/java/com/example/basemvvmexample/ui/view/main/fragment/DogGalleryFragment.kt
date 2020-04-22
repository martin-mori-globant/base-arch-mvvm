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
import com.example.basemvvmexample.databinding.DogGalleryFragmentBinding
import com.example.basemvvmexample.ui.adapter.DogGalleryRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.DogGalleryViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.dog_gallery_fragment.dog_gallery_fragment_breed
import kotlinx.android.synthetic.main.dog_gallery_fragment.dog_gallery_fragment_recycler_view
import kotlinx.android.synthetic.main.dog_gallery_fragment.dog_gallery_fragment_sub_breed

class DogGalleryFragment : Fragment() {

    private lateinit var viewModel: DogGalleryViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: DogGalleryFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.dog_gallery_fragment, container, false)
        binding.handler = this
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DogGalleryViewModel::class.java)
        sharedViewModel = activity?.let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }!!
    }

    override fun onStart() {
        super.onStart()
        dog_gallery_fragment_breed.text = sharedViewModel.breed.value
        dog_gallery_fragment_sub_breed.text = sharedViewModel.subBreed.value
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewAdapter = DogGalleryRecyclerViewAdapter(
                listOf(getString(R.string.img_dog_1), getString(R.string.img_dog_2), getString(R.string.img_dog_3)),
                sharedViewModel)
        dog_gallery_fragment_recycler_view.adapter = recyclerViewAdapter
        dog_gallery_fragment_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun execAction() = NavHostFragment.findNavController(this).navigate(R.id.action_dogGalleryFragment_to_breedFragment)
}
