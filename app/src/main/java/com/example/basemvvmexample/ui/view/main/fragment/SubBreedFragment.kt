package com.example.basemvvmexample.ui.view.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvmexample.R
import com.example.basemvvmexample.databinding.SubBreedFragmentBinding
import com.example.basemvvmexample.ui.adapter.SubBreedRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import com.example.basemvvmexample.ui.viewmodel.SubBreedViewModel
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_recycler_view
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubBreedFragment : Fragment() {

    private val viewModel by viewModel<SubBreedViewModel>()
    private val sharedViewModel by sharedViewModel<SharedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = SubBreedFragmentBinding.inflate(inflater, container, false).apply {
            holder = this@SubBreedFragment
            breed = sharedViewModel.breed.value
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        breed_fragment_recycler_view.adapter = SubBreedRecyclerViewAdapter(
                listOf(getString(R.string.sub_dog_1), getString(R.string.sub_dog_2), getString(R.string.sub_dog_3)),
                sharedViewModel)
        breed_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    fun execAction() = NavHostFragment.findNavController(this).navigate(R.id.action_subBreedFragment_to_dogGalleryFragment)
}
