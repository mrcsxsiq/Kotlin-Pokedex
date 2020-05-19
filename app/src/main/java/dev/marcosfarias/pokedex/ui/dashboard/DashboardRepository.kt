package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.MutableLiveData
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon

class DashboardRepository(
    private val pokemonDAO: PokemonDAO
) {
    suspend fun getPokemonById(
        id: Int
    ): Pokemon {
        return pokemonDAO.getById(id)
    }
}
