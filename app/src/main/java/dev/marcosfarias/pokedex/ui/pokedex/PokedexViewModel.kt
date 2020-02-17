package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.marcosfarias.pokedex.model.Pokemon
import kotlinx.coroutines.Dispatchers

class PokedexViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    fun getListPokemon(): LiveData<List<Pokemon>> = liveData(
        context = Dispatchers.IO,
        block = {
            emit(pokemonRepository.pokemonList())
        }
    )
}