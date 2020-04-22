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
import com.example.basemvvmexample.databinding.SubBreedFragmentBinding
import com.example.basemvvmexample.ui.adapter.SubBreedRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import com.example.basemvvmexample.ui.viewmodel.SubBreedViewModel
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_recycler_view
import kotlinx.android.synthetic.main.sub_breed_fragment.sub_breed_fragment_text

class SubBreedFragment : Fragment() {

    private lateinit var viewModel: SubBreedViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: SubBreedFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.sub_breed_fragment, container, false)
        binding.handler = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SubBreedViewModel::class.java)
        sharedViewModel = activity?.let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }!!
    }

    override fun onStart() {
        super.onStart()
        sub_breed_fragment_text.text = sharedViewModel.breed.value
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewAdapter = SubBreedRecyclerViewAdapter(
                listOf(getString(R.string.sub_dog_1), getString(R.string.sub_dog_2), getString(R.string.sub_dog_3)),
                sharedViewModel)
        breed_fragment_recycler_view.adapter = recyclerViewAdapter
        breed_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    fun execAction() = NavHostFragment.findNavController(this).navigate(R.id.action_subBreedFragment_to_dogGalleryFragment)
}
