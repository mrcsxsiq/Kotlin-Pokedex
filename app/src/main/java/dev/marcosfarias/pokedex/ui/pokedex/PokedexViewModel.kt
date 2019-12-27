package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.pokedex.App
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.repository.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokedexViewModel : ViewModel() {

    private var listPokemon = MutableLiveData<List<Pokemon>>()
    private val pokemonDAO : PokemonDAO = App.database!!.pokemonDAO()

    init {
        initNetworkRequest()
    }

    private fun initNetworkRequest() {
        val call = APIService().pokemonService().get()
        call.enqueue(object: Callback<List<Pokemon>?> {
            override fun onResponse(call: Call<List<Pokemon>?>?, response: Response<List<Pokemon>?>?) {
                response?.body()?.let {
                    val pokemons: List<Pokemon> = it
                    Thread(Runnable {
                        for (pokemon in pokemons){
                            pokemonDAO.add(pokemon)
                        }
                    }).start()
                    listPokemon.value = pokemons
                }
            }

            override fun onFailure(call: Call<List<Pokemon>?>?, t: Throwable?) {
               listPokemon.value = pokemonDAO.all().value
            }
        })

    }

    fun getListPokemon(): LiveData<List<Pokemon>> {
        return pokemonDAO.all()
    }


}

