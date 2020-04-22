package com.example.basemvvmexample.ui.view.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvmexample.R
import com.example.basemvvmexample.databinding.BreedFragmentBinding
import com.example.basemvvmexample.ui.adapter.BreedRecyclerViewAdapter
import com.example.basemvvmexample.ui.viewmodel.BreedViewModel
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.breed_fragment.breed_fragment_recycler_view
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BreedFragment : Fragment() {

    private val viewModel by viewModel<BreedViewModel>()
    private val sharedViewModel by sharedViewModel<SharedViewModel>()
    private lateinit var binding: BreedFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BreedFragmentBinding.inflate(inflater, container, false).apply {
            holder = this@BreedFragment
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        breed_fragment_recycler_view.adapter = BreedRecyclerViewAdapter(
                listOf(getString(R.string.dog_1), getString(R.string.dog_2), getString(R.string.dog_3)),
                sharedViewModel)
        breed_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    fun execAction() = NavHostFragment.findNavController(this).navigate(R.id.action_breedFragment_to_subBreedFragment)
}
