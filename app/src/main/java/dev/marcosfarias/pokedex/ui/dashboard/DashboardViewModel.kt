package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.model.ChainLink
import dev.marcosfarias.pokedex.model.Pokemon
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val pokemon = MutableLiveData<Pokemon>()
    private val pokemonEvolution = MutableLiveData<Pokemon>()

    fun getPokemonById(id: Int): LiveData<Pokemon> {
        viewModelScope.launch {
            dashboardRepository.getPokemonById(id, pokemon)
        }
        return pokemon
    }

    fun getEvolutionsByChainLink(link: ChainLink): LiveData<Pokemon> {
        viewModelScope.launch {
            dashboardRepository.getPokemonByName(link.species.name, pokemonEvolution)
        }

        if (!link.evolvesTo.isNullOrEmpty()) {
            getEvolutionsByChainLink(link.evolvesTo.first())
            return pokemonEvolution
        }
        return pokemonEvolution
    }
}
