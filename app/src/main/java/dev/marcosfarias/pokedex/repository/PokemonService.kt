package dev.marcosfarias.pokedex.repository

import dev.marcosfarias.pokedex.model.Pokemon
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon.json")
    fun get(): List<Pokemon>
}
