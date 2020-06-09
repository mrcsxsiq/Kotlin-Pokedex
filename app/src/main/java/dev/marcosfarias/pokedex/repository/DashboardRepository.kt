package dev.marcosfarias.pokedex.repository

import androidx.lifecycle.MutableLiveData
import dev.marcosfarias.pokedex.database.dao.EvolutionChainDAO
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.database.dao.SpeciesDAO
import dev.marcosfarias.pokedex.model.EvolutionChain
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.model.Species
import dev.marcosfarias.pokedex.model.commons.toModel
import kotlinx.coroutines.runBlocking
import me.sargunvohra.lib.pokekotlin.client.RxPokeApiClient
import me.sargunvohra.lib.pokekotlin.model.ChainLink
import rx.Observable
import rx.schedulers.Schedulers

class DashboardRepository(
    private val pokemonApi: RxPokeApiClient,
    private val pokemonDAO: PokemonDAO,
    private val speciesDAO: SpeciesDAO,
    private val evolutionChainDAO: EvolutionChainDAO
) {

    var evolutionIdList = mutableListOf<Int>()

    suspend fun loadPokemon(
        id: Int,
        pokemon: MutableLiveData<Pokemon>
    ) {
        pokemon.postValue(pokemonDAO.get(id))
    }

    suspend fun loadSpecies(
        id: Int,
        speciesData: MutableLiveData<Species>
    ) {
        val cache = speciesDAO.get(id)
        cache?.let { species ->
            speciesData.postValue(species)
            return
        }
        getSpecies(id)
            .subscribeOn(Schedulers.io())
            .subscribe ({ pokemonSpecies ->
                speciesDAO.add(pokemonSpecies)
                speciesData.postValue(pokemonSpecies)
            }, { error ->
                error.printStackTrace()
            })
    }

    suspend fun loadEvolutionChain(
        id: Int,
        evolutionChainData: MutableLiveData<MutableList<Pokemon>>
    ) {
        val pokemon = pokemonDAO.get(id)
        val species = speciesDAO.get(pokemon.species.id)
        species?.let { speciesValue ->
            val cache = evolutionChainDAO.get(speciesValue.evolutionChain.id)
            cache?.let { evolutionChain ->
                getEvolutionList(evolutionChain.chain)
                evolutionChainData.postValue(pokemonDAO.getList(evolutionIdList))
                return
            }
            getEvolutionChain(speciesValue.evolutionChain.id)
                .subscribeOn(Schedulers.io())
                .subscribe({ evolutionChain ->
                    evolutionChainDAO.add(evolutionChain)
                    getEvolutionList(evolutionChain.chain)
                }, { error ->
                    error.printStackTrace()
                }, {
                    runBlocking {
                        evolutionChainData.postValue(pokemonDAO.getList(evolutionIdList))
                    }
                })
        }
    }

    private fun getSpecies(id: Int): Observable<Species> {
        return pokemonApi.getPokemonSpecies(id).flatMap { species ->
            Observable.just(species.toModel())
        }
    }

    private fun getEvolutionChain(id: Int): Observable<EvolutionChain> {
        return pokemonApi.getEvolutionChain(id).flatMap { evolutionChain ->
            Observable.just(evolutionChain.toModel())
        }
    }

    private fun getEvolutionList(chain: ChainLink) {
        evolutionIdList.add(chain.species.id)

        if (chain.evolvesTo.isNotEmpty()) {
            getEvolutionList(chain.evolvesTo.first())
        } else {
            evolutionIdList.sortedBy { it }
        }
    }
}
