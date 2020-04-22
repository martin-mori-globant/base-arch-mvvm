package com.example.basemvvmexample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.basemvvmexample.R
import com.example.basemvvmexample.ui.viewmodel.SharedViewModel

class SubBreedRecyclerViewAdapter(
    private val list: List<String>,
    private val viewModel: ViewModel
) : RecyclerView.Adapter<SubBreedRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.sub_breed_fragment_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position]
        holder.textView.setOnClickListener { (viewModel as SharedViewModel).subBreed(list[position]) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.sub_breed_fragment_list_item_text)
    }
}
