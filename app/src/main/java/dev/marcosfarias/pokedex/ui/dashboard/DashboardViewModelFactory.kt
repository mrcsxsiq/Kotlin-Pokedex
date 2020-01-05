package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DashboardViewModelFactory(private val id: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}