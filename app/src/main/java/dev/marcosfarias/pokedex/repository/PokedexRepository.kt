package dev.marcosfarias.pokedex.repository

import androidx.lifecycle.MutableLiveData
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.model.commons.toModel
import me.sargunvohra.lib.pokekotlin.client.RxPokeApiClient
import rx.Observable
import rx.schedulers.Schedulers

class PokedexRepository(
    private val pokemonApi: RxPokeApiClient,
    private val pokemonDAO: PokemonDAO
) {

    private var loadingLock = false
    private var pokedexList = mutableListOf<Pokemon>()

    suspend fun loadPokedexList(
        offset: Int,
        limit: Int,
        isLoadingData: MutableLiveData<Boolean>,
        pokedexListData: MutableLiveData<List<Pokemon>>
    ) {
        if (!loadingLock) {
            loadingLock = true
            isLoadingData.postValue(loadingLock)
            val cache = pokemonDAO.getRange(offset)
            if (cache.isNotEmpty()) {
                val data = pokemonDAO.getAll()
                pokedexListData.postValue(data)
                loadingLock = false
                isLoadingData.postValue(loadingLock)
            } else {
                getPokedexList(offset, limit)
                    .subscribeOn(Schedulers.io())
                    .subscribe({ pokedexItem ->
                        pokedexList.add(pokedexItem)
                        pokemonDAO.add(pokedexItem)
                    }, { error ->
                        error.printStackTrace()
                        loadingLock = false
                        isLoadingData.postValue(loadingLock)
                    }, {
                        pokedexListData.postValue(pokedexList)
                        loadingLock = false
                        isLoadingData.postValue(loadingLock)
                    })
            }
        }
    }

    private fun getPokedexList(
        offset: Int,
        limit: Int): Observable<Pokemon> {
        return pokemonApi.getPokemonList(offset, limit).flatMapIterable { resource ->
            resource.results
        }.flatMap { results ->
            pokemonApi.getPokemon(results.id).flatMap { pokemon ->
                Observable.just(pokemon.toModel())
            }
        }
    }
}