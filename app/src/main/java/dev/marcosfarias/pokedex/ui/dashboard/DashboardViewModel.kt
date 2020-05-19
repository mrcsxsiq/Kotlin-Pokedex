package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.model.Pokemon
import kotlinx.coroutines.launch

class DashboardViewModel(
    val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _pokemon = MutableLiveData<Pokemon>()

    val pokemon: LiveData<Pokemon> get() = _pokemon

    fun getPokemonById(id: Int) {
        viewModelScope.launch {
            _pokemon.value = dashboardRepository.getPokemonById(id)
        }
    }
}
