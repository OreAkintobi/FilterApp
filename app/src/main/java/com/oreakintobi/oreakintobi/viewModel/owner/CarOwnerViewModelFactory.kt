package com.oreakintobi.oreakintobi.viewModel.owner


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oreakintobi.oreakintobi.models.FilterElement

class CarOwnerViewModelFactory(private val dataSource: FilterElement) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarOwnerViewModel::class.java)) {
            return CarOwnerViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

