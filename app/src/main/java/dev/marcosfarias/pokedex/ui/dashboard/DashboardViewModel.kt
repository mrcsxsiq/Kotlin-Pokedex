package dev.marcosfarias.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import kotlinx.coroutines.Dispatchers

class DashboardViewModel(
    private val pokemonDAO: PokemonDAO
) : ViewModel() {

    fun getPokemonById(id: String): LiveData<Pokemon> = liveData(
        context = Dispatchers.IO,
        block = {
            emit(pokemonDAO.getById(id))
        }
    )
}
