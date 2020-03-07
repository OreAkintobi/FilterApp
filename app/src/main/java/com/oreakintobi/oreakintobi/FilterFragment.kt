package com.oreakintobi.oreakintobi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.oreakintobi.oreakintobi.databinding.FragmentFilterBinding

/**
 * A simple [Fragment] subclass.
 */
class FilterFragment : Fragment() {
    private lateinit var filterViewModel: FilterViewModel
    private lateinit var binding: FragmentFilterBinding
    lateinit var adapter: FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false)
        filterViewModel =
            ViewModelProvider(this).get((FilterViewModel::class.java))
        binding.filterViewModel = filterViewModel
        binding.lifecycleOwner = this

//           binding.recyclerView.adapter = adapter

        return binding.root
    }

}
