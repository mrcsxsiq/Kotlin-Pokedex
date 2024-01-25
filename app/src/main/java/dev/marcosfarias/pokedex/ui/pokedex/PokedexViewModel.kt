package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.repository.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class PokedexViewModel(
    private val pokemonDAO: PokemonDAO,
    private val pokemonService: PokemonService
) : ViewModel() {

    init {
        initNetworkRequest()
    }

    private fun initNetworkRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = pokemonService.get()
                pokemonDAO.add(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getListPokemon() = pokemonDAO.all()
}
