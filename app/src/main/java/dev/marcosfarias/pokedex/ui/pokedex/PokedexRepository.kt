package dev.marcosfarias.pokedex.ui.pokedex

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.EvolutionChain
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.model.PokemonSpecies
import dev.marcosfarias.pokedex.repository.PokemonService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class PokedexRepository(
    private val pokemonDAO: PokemonDAO,
    private val pokemonService: PokemonService
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
            val cache = pokemonDAO.getByRangeOffsetId(offset)
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
        return pokemonService.getNextList(offset, limit)
            .flatMap { pokemonResults ->
                Observable.fromIterable(pokemonResults.results)
            }.flatMap { pokemonName ->
                Observable.zip(
                    pokemonService.getById(Uri.parse(pokemonName.url).lastPathSegment.toString()),
                    Observable.zip(
                        pokemonService.getSpeciesById(Uri.parse(pokemonName.url).lastPathSegment.toString()),
                        pokemonService.getEvolutionChainById(Uri.parse(pokemonName.url).lastPathSegment.toString()),
                        io.reactivex.functions.BiFunction<PokemonSpecies, EvolutionChain, PokemonSpecies> { pokemonSpecies, evolutionChain ->
                            return@BiFunction PokemonSpecies (
                                pokemonSpecies.id,
                                pokemonSpecies.name,
                                pokemonSpecies.order,
                                pokemonSpecies.genderRate,
                                pokemonSpecies.captureRate,
                                pokemonSpecies.baseHappiness,
                                pokemonSpecies.isBaby,
                                pokemonSpecies.hatchCounter,
                                pokemonSpecies.hasGenderDifferences,
                                pokemonSpecies.formsSwitchable,
                                pokemonSpecies.growthRate,
                                pokemonSpecies.pokedexNumbers,
                                pokemonSpecies.eggGroups,
                                pokemonSpecies.color,
                                pokemonSpecies.shape,
                                pokemonSpecies.evolvesFromSpecies,
                                evolutionChain,
                                pokemonSpecies.habitat,
                                pokemonSpecies.generation,
                                pokemonSpecies.names,
                                pokemonSpecies.palParkEncounters,
                                pokemonSpecies.formDescriptions,
                                pokemonSpecies.genera,
                                pokemonSpecies.varieties,
                                pokemonSpecies.flavorTextEntries
                            )
                        }
                    ),
                    io.reactivex.functions.BiFunction<Pokemon, PokemonSpecies, Pokemon> { pokemon, species ->
                        return@BiFunction Pokemon(
                            pokemon.id,
                            pokemon.name,
                            pokemon.baseExperience,
                            pokemon.height,
                            pokemon.isDefault,
                            pokemon.order,
                            pokemon.weight,
                            species,
                            pokemon.abilities,
                            pokemon.forms,
                            pokemon.gameIndices,
                            pokemon.heldItems,
                            pokemon.moves,
                            pokemon.stats,
                            pokemon.types,
                            pokemon.sprites
                        )
                    }
                )
            }
    }
}