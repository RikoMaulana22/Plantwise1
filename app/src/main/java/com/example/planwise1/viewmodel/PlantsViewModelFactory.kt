package com.example.planwise1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlantsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantsViewModel::class.java)) {
            return PlantsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}