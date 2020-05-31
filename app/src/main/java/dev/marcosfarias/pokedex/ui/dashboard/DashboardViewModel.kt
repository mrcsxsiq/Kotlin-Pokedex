package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.model.Pokemon
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val pokemon = MutableLiveData<Pokemon>()

    val _pokemon: LiveData<Pokemon> = pokemon

    fun getPokemonById(id: Int): LiveData<Pokemon> {
        viewModelScope.launch {
            dashboardRepository.getPokemonById(id, pokemon)
        }
        return _pokemon
    }

//    fun getPokemonEvolutionsByIds(ids: List<String>): LiveData<List<Pokemon>> {
//        return pokemonDAO.getEvolutionsByIds(ids)
//    }
}
