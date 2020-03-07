package com.oreakintobi.oreakintobi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oreakintobi.oreakintobi.databinding.FilterItemBinding

class FilterAdapter(
    private var filters: List<Filter>,
    private var context: Context
) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    private lateinit var binding: FilterItemBinding

    class FilterViewHolder(private val binding: FilterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Filter) {
            binding.filter = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterViewHolder {
        val inflater = LayoutInflater.from(context)
        binding = FilterItemBinding.inflate(inflater, parent, false)
        return FilterViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return filters.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = filters[position]
        holder.bind(filter)
    }
}