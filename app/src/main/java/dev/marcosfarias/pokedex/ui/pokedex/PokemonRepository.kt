package dev.marcosfarias.pokedex.ui.pokedex

import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.repository.PokemonService

class PokemonRepository(
    private val pokemonDAO: PokemonDAO,
    private val pokemonService: PokemonService
) {
    suspend fun pokemonList(): List<Pokemon> {
        val cache = pokemonDAO.all()
        if (cache.isNotEmpty()) {
            return cache
        }
        val network = pokemonService.get().body() ?: emptyList()
        pokemonDAO.add(network)
        return network
    }
}