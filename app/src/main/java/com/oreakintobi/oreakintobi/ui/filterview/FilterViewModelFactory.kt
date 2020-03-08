package com.oreakintobi.oreakintobi.ui.filterview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oreakintobi.oreakintobi.repositories.FilterRepository

class FilterViewModelFactory(private val repository: FilterRepository) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilterViewModel::class.java)) {
            return FilterViewModel(
                repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}