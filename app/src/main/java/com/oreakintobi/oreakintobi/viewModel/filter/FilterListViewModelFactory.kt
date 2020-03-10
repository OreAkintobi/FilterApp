package com.oreakintobi.oreakintobi.viewModel.filter


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oreakintobi.oreakintobi.repository.FilterRepository

class FilterListViewModelFactory(private val dataSource: FilterRepository) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilterListViewModel::class.java)) {
            return FilterListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

