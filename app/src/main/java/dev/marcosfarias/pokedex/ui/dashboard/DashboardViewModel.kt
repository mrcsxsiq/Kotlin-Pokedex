package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon

class DashboardViewModel(private val pokemonDAO: PokemonDAO) : ViewModel() {

    fun getPokemonById(id: String?): LiveData<Pokemon> {
        return pokemonDAO.getById(id)
    }

    fun getPokemonEvolutionsByIds(ids: List<String>): LiveData<List<Pokemon>> {
        return pokemonDAO.getEvolutionsByIds(ids)
    }
}
