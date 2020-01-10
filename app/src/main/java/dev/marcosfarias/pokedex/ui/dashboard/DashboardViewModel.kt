package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.pokedex.App
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon

class DashboardViewModel : ViewModel() {

    private val pokemonDAO: PokemonDAO = App.database.pokemonDAO()

    fun getPokemonById(id: String?): LiveData<Pokemon> {
        return pokemonDAO.getById(id)
    }

}
