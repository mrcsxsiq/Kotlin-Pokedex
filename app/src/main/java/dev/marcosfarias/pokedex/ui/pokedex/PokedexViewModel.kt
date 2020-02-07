package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.repository.PokemonService
import kotlin.concurrent.thread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokedexViewModel(private val pokemonDAO: PokemonDAO, private val pokemonService: PokemonService) : ViewModel() {

    init {
        initNetworkRequest()
    }

    private fun initNetworkRequest() {
        val call = pokemonService.get()
        call.enqueue(object : Callback<List<Pokemon>?> {
            override fun onResponse(
                call: Call<List<Pokemon>?>?,
                response: Response<List<Pokemon>?>?
            ) {
                response?.body()?.let { pokemons: List<Pokemon> ->
                    thread {
                        pokemonDAO.add(pokemons)
                    }
                }
            }

            override fun onFailure(call: Call<List<Pokemon>?>?, t: Throwable?) {
                // TODO handle failure
            }
        })
    }

    fun getListPokemon(): LiveData<List<Pokemon>> {
        return pokemonDAO.all()
    }
}
