package com.example.basemvvmexample.ui.view.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvmexample.R
import com.example.basemvvmexample.databinding.DogGalleryFragmentBinding
import com.example.basemvvmexample.ui.adapter.DogGalleryRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.DogGalleryViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.dog_gallery_fragment.dog_gallery_fragment_recycler_view
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DogGalleryFragment : Fragment() {

    private val viewModel by inject<DogGalleryViewModel>()
    private val sharedViewModel by sharedViewModel<SharedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DogGalleryFragmentBinding.inflate(inflater, container, false).apply {
            holder = this@DogGalleryFragment
            breed = sharedViewModel.breed.value
            subBreed = sharedViewModel.subBreed.value
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        dog_gallery_fragment_recycler_view.adapter = DogGalleryRecyclerViewAdapter(
                listOf(getString(R.string.img_dog_1), getString(R.string.img_dog_2), getString(R.string.img_dog_3)),
                sharedViewModel)
        dog_gallery_fragment_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun execAction() = NavHostFragment.findNavController(this).navigate(R.id.action_dogGalleryFragment_to_mainFragment)
}
