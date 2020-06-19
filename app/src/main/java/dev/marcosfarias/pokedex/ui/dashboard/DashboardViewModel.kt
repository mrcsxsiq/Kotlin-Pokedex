package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.model.Species
import dev.marcosfarias.pokedex.repository.DashboardRepository
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val pokemon = MutableLiveData<Pokemon>()
    private val speciesData = MutableLiveData<Species>()
    private val evolutionPokemonList = MutableLiveData<MutableList<Pokemon>>()

    fun getPokemon(id: Int): LiveData<Pokemon> {
        viewModelScope.launch {
            dashboardRepository.loadPokemon(
                id = id,
                pokemon = pokemon
            )
        }
        return pokemon
    }

    fun getSpecies(
        id: Int
    ): LiveData<Species> {
        viewModelScope.launch {
            dashboardRepository.loadSpecies(
                id = id,
                speciesData = speciesData
            )
        }
        return speciesData
    }

    fun getEvolutionChain(
        id: Int
    ): LiveData<MutableList<Pokemon>> {
        viewModelScope.launch {
            dashboardRepository.loadEvolutionChain(
                id = id,
                evolutionChainData = evolutionPokemonList
            )
        }
        return evolutionPokemonList
    }
}
