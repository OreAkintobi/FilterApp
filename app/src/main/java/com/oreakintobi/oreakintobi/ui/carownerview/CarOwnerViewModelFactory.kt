package com.oreakintobi.oreakintobi.ui.carownerview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CarOwnerViewModelFactory() :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarOwnerViewModel::class.java)) {
            return CarOwnerViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}